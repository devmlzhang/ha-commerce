package com.ha.file.controller;


import com.ha.common.enums.DictEnum;
import com.ha.common.response.ResponseResult;
import com.ha.file.pojo.PubArea;
import com.ha.file.pojo.SysDict;
import com.ha.file.request.FileRelationReq;
import com.ha.file.request.PubAddressReq;
import com.ha.file.service.FileService;
import com.ha.file.service.PubAddressService;
import com.ha.file.service.PubAreaService;
import com.ha.file.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>
 *    公共接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
@RestController
@RequestMapping("/common")
@Api(value = "公共接口(文件、字典、地址)")
@Slf4j
public class CommonController {

    @Autowired
    private PubAreaService areaService;
    @Autowired
    private PubAddressService pubAddressService;
    @Autowired
    private FileService fileService;
    @Autowired
    private SysDictService dictService ;


    @GetMapping(value = "/areasByPId")
    @ApiOperation(value = "查询省|市下面的子集(省 pid = 0 )")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid",value = "父id",required=true,dataTypeClass = String.class),
    })
    public ResponseResult<List<PubArea>> areasByPId(String pId) {
        try {
            return ResponseResult.successResult(areaService.listByPId(pId));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异常：", e);
        }
        return ResponseResult.errorResult();
    }


    @GetMapping("/dictByType")
    @ApiOperation(value = "根据组名查询数据字典列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "组名类型",required=true,dataTypeClass = Integer.class),
    })
    public ResponseResult dictByType(Integer type) {
        if( type != null ){
            DictEnum dictEnum = DictEnum.valueOfEnum(type);
            if( dictEnum != null ){
                return ResponseResult.successResult(dictService.findByGroup(dictEnum.getValue()));
            }
        }
        return ResponseResult.errorResult();
    }


    @GetMapping("/dictByPvalueAndPtype")
    @ApiOperation(value = "根据父value获取数据字典值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pValue",value = "属性值",required=true,dataTypeClass = String.class),
            @ApiImplicitParam(name = "pType",value = "组别",required=true,dataTypeClass = Integer.class),
    })
    public ResponseResult<List<SysDict>> dictByPid(String pValue,Integer pType) {
        if( StringUtils.isNotBlank(pValue)&&pType!=null ){
            DictEnum dictEnum = DictEnum.valueOfEnum(pType);
            List<SysDict>  list = dictService.findByPValue(pValue,dictEnum.getValue()) ;
            return ResponseResult.successResult(list);
        }
        return ResponseResult.errorResult();
    }


    @GetMapping("/saveOrUpdateAddress")
    @ApiOperation(value = "添加或者更新地址信息")
    public ResponseResult saveOrUpdateAddress(@RequestBody  @Valid PubAddressReq model, BindingResult result){
        if ( result.hasErrors()) {
            FieldError fieldError = result.getFieldErrors().get(0);
            return ResponseResult.paramsErrorResult(fieldError.toString());
        }
        return ResponseResult.successResult(pubAddressService.saveOrUpdateAddress(model));
    }


    @GetMapping("/queryByBizIdAndType")
    @ApiOperation(value = "根据业务ID和业务类型进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizId",value = "业务id",required=true,dataTypeClass = String.class),
            @ApiImplicitParam(name = "type",value = "业务类型",required=true,dataTypeClass = Integer.class),
    })
    public ResponseResult queryByBizIdAndType(String bizId, Integer type){
        return ResponseResult.successResult(pubAddressService.queryByBizIdAndType(bizId,type));
    }


    @PostMapping(value = "/fileInfoSave")
    @ApiOperation(value = "文件信息保存")
    public ResponseResult fileInfoSave(@RequestBody FileRelationReq req) {
        try {
            return fileService.fileInfoSave(req);
        } catch (Exception e) {
            log.error("文件信息保存异常:{}",e);
        }
        return ResponseResult.errorResult("文件信息保存异常");
    }

    @PostMapping(value = "/upload")
    @ApiOperation(value = "文件上传")
    public ResponseResult upload(@RequestParam("file") MultipartFile files) {
        try {
            return fileService.upload(files);
        } catch (Exception e) {
            log.error("文件上传异常:{}",e);
        }
        return ResponseResult.errorResult("文件上传异常");
    }


    @GetMapping(path = "/delete")
    @ApiOperation(value = "文件删除")
    public ResponseResult access(@RequestParam(value = "fileKey", required = true) String fileKey) throws IOException {
        return fileService.delete(fileKey);
    }


    @GetMapping(value = ("/down"))
    @ApiOperation(value = "下载文件")
    public void downFile(OutputStream os, @RequestParam(value = "fileKey",required = true) String fileKey) throws IOException{
        fileService.downFile(os,fileKey);
    }



}