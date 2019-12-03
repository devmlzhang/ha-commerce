package com.ha.flow.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ha.flow.utils.ActivitiUtils;
import com.ha.flow.utils.RestMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  部署流程、删除流程
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/12/3
 */

@RestController
@Api(tags = "部署流程、删除流程")
@Slf4j
public class DeployController extends BaseController {
    @Autowired
    private ProcessDiagramGenerator processDiagramGenerator;


    @PostMapping(path = "deploy")
    @ApiOperation(value = "根据modelId部署流程", notes = "根据modelId部署流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId", value = "设计的流程图模型ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "processName", value = "设计的流程图名称", dataType = "String", paramType = "query")

    })
    public RestMessage deploy(@RequestParam("modelId") String modelId, @RequestParam("processName") String processName) {
        RestMessage restMessage = new RestMessage();
        Deployment deployment = null;
        try {
            byte[] sourceBytes = repositoryService.getModelEditorSource(modelId);
            JsonNode editorNode = new ObjectMapper().readTree(sourceBytes);
            BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();
            BpmnModel bpmnModel = bpmnJsonConverter.convertToBpmnModel(editorNode);
            DeploymentBuilder deploymentBuilder = repositoryService.createDeployment()
                    .name("手动部署")
                    .enableDuplicateFiltering()
                    .addBpmnModel(processName.concat(".bpmn20.xml"), bpmnModel);
            deployment = deploymentBuilder.deploy();
        } catch (Exception e) {
            restMessage = RestMessage.fail("部署失败", e.getMessage());
            log.error("根据modelId部署流程,异常:{}", e);
        }

        if (deployment != null) {
            Map<String, String> result = new HashMap<>(2);
            result.put("deploymentId", deployment.getId());
            result.put("deploymentName", deployment.getName());
            restMessage = RestMessage.success("部署成功", result);
        }
        return restMessage;
    }


    @PostMapping(path = "deleteProcess")
    @ApiOperation(value = "根据部署ID删除流程", notes = "根据部署ID删除流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deploymentId", value = "部署ID", dataType = "String", paramType = "query", example = "")
    })
    public RestMessage deleteProcess(@RequestParam("deploymentId") String deploymentId) {
        RestMessage restMessage = new RestMessage();
        try {
            /**不带级联的删除：只能删除没有启动的流程，如果流程启动，就会抛出异常*/
            repositoryService.deleteDeployment(deploymentId);
//            /**级联删除：不管流程是否启动，都能可以删除（emmm大概是一锅端）*/
//            repositoryService.deleteDeployment(deploymentId, true);
            restMessage = RestMessage.success("删除成功", null);
        } catch (Exception e) {
            restMessage = RestMessage.fail("删除失败", e.getMessage());
            log.error("根据部署ID删除流程,异常:{}", e);
        }
        return restMessage;
    }


    @GetMapping(value="/showImg")
    public void showImg(String instanceId, HttpServletResponse response) throws IOException {
        /*
         * 参数校验
         */
        System.out.println("查看完整流程图！流程实例ID:{}"+instanceId);
        if(StringUtils.isBlank(instanceId)) {
            return;
        }

        /*
         *  获取流程实例
         */
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        if(processInstance == null) {
            System.out.println("流程实例ID:{}没查询到流程实例！"+instanceId);
            return;
        }

        // 根据流程对象获取流程对象模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());


        /*
         *  查看已执行的节点集合
         *  获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
         */
        // 构造历史流程查询
        HistoricActivityInstanceQuery historyInstanceQuery = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceId);
        // 查询历史节点
        List<HistoricActivityInstance> historicActivityInstanceList = historyInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();
        if(historicActivityInstanceList == null || historicActivityInstanceList.size() == 0) {
            System.out.println("流程实例ID:{}没有历史节点信息！"+ instanceId);
            outputImg(response, bpmnModel, null, null);
            return;
        }
        // 已执行的节点ID集合(将historicActivityInstanceList中元素的activityId字段取出封装到executedActivityIdList)
        List<String> executedActivityIdList = historicActivityInstanceList.stream().map(item -> item.getActivityId()).collect(Collectors.toList());

        /*
         *  获取流程走过的线
         */
        // 获取流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
        List<String> flowIds = ActivitiUtils.getHighLightedFlows(bpmnModel, processDefinition, historicActivityInstanceList);

        /*
         * 输出图像，并设置高亮
         */
        outputImg(response, bpmnModel, flowIds, executedActivityIdList);
    }

    /**
     * 输出图像
     * @param response 响应实体
     * @param bpmnModel 图像对象
     * @param flowIds 已执行的线集合
     * @param executedActivityIdList 已执行的节点ID集合
     * @throws IOException
     */
    private void outputImg(HttpServletResponse response, BpmnModel bpmnModel, List<String> flowIds, List<String> executedActivityIdList) throws IOException {
        InputStream imageStream = null;
        try {
            imageStream = processDiagramGenerator.generateDiagram(bpmnModel, executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", true, "png");
            // 输出资源内容到相应对象
            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
            response.getOutputStream().flush();
        }catch(Exception e) {
        } finally { // 流关闭
            imageStream.close();
        }
    }




}