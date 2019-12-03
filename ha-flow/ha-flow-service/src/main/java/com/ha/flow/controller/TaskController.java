package com.ha.flow.controller;

import com.ha.flow.utils.RestMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  任务相关接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/12/3
 */
@RestController
@Api(tags = "任务相关接口")
@Slf4j
public class TaskController extends BaseController {

    @PostMapping(path = "findTaskByAssignee")
    @ApiOperation(value = "根据流程assignee查询当前人的个人任务", notes = "根据流程assignee查询当前人的个人任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assignee", value = "代理人（当前用户）", dataType = "String", paramType = "query"),
    })
    public RestMessage findTaskByAssignee(@RequestParam("assignee") String assignee) {
        RestMessage restMessage = new RestMessage();

        try {
            //指定个人任务查询
            List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();
            if (CollectionUtil.isNotEmpty(taskList)) {
                List<Map<String, String>> resultList = new ArrayList<>();
                for (Task task : taskList) {
                    Map<String, String> resultMap = new HashMap<>();
                    /* 任务ID */
                    resultMap.put("taskID", task.getId());
                    /* 任务名称 */
                    resultMap.put("taskName", task.getName());
                    /* 任务的创建时间 */
                    resultMap.put("taskCreateTime", task.getCreateTime().toString());
                    /* 任务的办理人 */
                    resultMap.put("taskAssignee", task.getAssignee());
                    /* 流程实例ID */
                    resultMap.put("processInstanceId", task.getProcessInstanceId());
                    /* 执行对象ID */
                    resultMap.put("executionId", task.getExecutionId());
                    /* 流程定义ID */
                    resultMap.put("processDefinitionId", task.getProcessDefinitionId());
                    resultList.add(resultMap);
                }
                restMessage = RestMessage.success("查询成功", resultList);
            } else {
                restMessage = RestMessage.success("查询成功", null);
            }
        } catch (Exception e) {
            restMessage = RestMessage.fail("查询失败", e.getMessage());
            log.error("根据流程assignee查询当前人的个人任务,异常:{}", e);
            return restMessage;
        }
        return restMessage;
    }


    @PostMapping(path = "completeTask")
    @ApiOperation(value = "完成任务", notes = "完成任务，任务进入下一个节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "variables", value = "填充参数", dataType = "body", paramType = "query"),
    })
    public RestMessage completeTask(@RequestParam("taskId") String taskId, String userName) {

        RestMessage restMessage = new RestMessage();
        Map<String, Object> variables = new HashMap<>();
        try {
            variables.put("holidayNum",2);
            taskService.complete(taskId, variables);
            restMessage = RestMessage.fail("完成任务成功", taskId);
        } catch (Exception e) {
            restMessage = RestMessage.fail("完成任务失败", e.getMessage());
            log.error("完成任务,异常:{}", e);
        }
        return restMessage;
    }


    @PostMapping(path = "turnTask")
    @ApiOperation(value = "任务转办", notes = "任务转办，把任务交给别人处理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userKey", value = "用户Key", dataType = "String", paramType = "query"),
    })
    public RestMessage turnTask(@RequestParam("taskId") String taskId, @RequestParam("userKey") String userKey) {
        RestMessage restMessage = new RestMessage();
        try {
            taskService.setAssignee(taskId, userKey);
            restMessage = RestMessage.fail("完成任务成功", taskId);
        } catch (Exception e) {
            restMessage = RestMessage.fail("完成任务失败", e.getMessage());
            log.error("任务转办,异常:{}", e);
        }
        return restMessage;
    }
}