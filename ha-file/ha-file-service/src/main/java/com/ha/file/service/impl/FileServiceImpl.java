package com.ha.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.ha.common.enums.StatusEnum;
import com.ha.common.response.ResponseResult;
import com.ha.common.utils.IDUtil;
import com.ha.file.config.AliyunConfig;
import com.ha.file.mapper.FileRelationMapper;
import com.ha.file.pojo.FileRelation;
import com.ha.file.request.FileRelationReq;
import com.ha.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private OSS ossClient;
    @Autowired
    private AliyunConfig aliyunConfig;
    @Autowired
    private FileRelationMapper fileRelationMapper;


    @Override
    public ResponseResult fileInfoSave(FileRelationReq req) {
        FileRelation fileRelation = new FileRelation();
        BeanUtils.copyProperties(req,fileRelation);
        fileRelation.setId(IDUtil.getId());
        fileRelation.setCreateTime(new Date());
        fileRelation.setStatus(StatusEnum.EFFECTIVE.getCode());
        int i = fileRelationMapper.insertSelective(fileRelation);
        if(i>0){
            return ResponseResult.successResult("添加成功！");
        }
        return ResponseResult.errorResult();
    }


    @Override
    public ResponseResult upload(MultipartFile uploadFile) {

        //文件新路径
        String fileName = uploadFile.getOriginalFilename();
        String filePath = getFilePath(fileName);
        // 上传到阿里云
        try {
            ossClient.putObject(aliyunConfig.getBucketName(), filePath, new
                    ByteArrayInputStream(uploadFile.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            //上传失败
            return ResponseResult.errorResult("文件上传失败！");
        }

        //this.aliyunConfig.getUrlPrefix() + filePath
        log.info("文件上传路径：{}",this.aliyunConfig.getUrlPrefix() + filePath);
        Map<String,String> fileInfo = new HashMap<>();
        fileInfo.put("url", filePath);
        return ResponseResult.successResult(fileInfo);
    }


    /**
     * 生成路径以及文件名
     * @param sourceFileName
     * @return
     */
    public String getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        return  dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }


    @Override
    public List<OSSObjectSummary> list() {
        // 设置最大个数。
        final int maxKeys = 200;
        // 列举文件。
        ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(aliyunConfig.getBucketName()).withMaxKeys(maxKeys));
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        return sums;
    }


    @Override
    public ResponseResult delete(String fileKey) {
        // 根据BucketName,fileName删除文件
        String fileName = this.aliyunConfig.getUrlPrefix() + fileKey;
        log.info("删除文件：{}",fileName);
        ossClient.deleteObject(aliyunConfig.getBucketName(), fileName);
        return ResponseResult.successResult("删除成功！");
    }

    @Override
    public void downFile(OutputStream os, String fileKey) throws IOException {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        String fileName = this.aliyunConfig.getUrlPrefix() + fileKey;
        OSSObject ossObject = ossClient.getObject(aliyunConfig.getBucketName(), fileName);
        // 读取文件内容。
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int lenght = 0;
        while ((lenght = in.read(buffer)) != -1) {
            out.write(buffer, 0, lenght);
        }
        if (out != null) {
            out.flush();
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }
}
