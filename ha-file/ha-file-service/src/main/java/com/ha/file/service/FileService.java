package com.ha.file.service;

import com.aliyun.oss.model.OSSObjectSummary;
import com.ha.common.response.ResponseResult;
import com.ha.file.request.FileRelationReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
/**
 * <p>
 *    文件服务
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
public interface FileService {

    /**
     *  文件上传
     * @param uploadFile
     * @return
     */
    public ResponseResult upload(MultipartFile uploadFile) ;


    /**
     * 查看文件列表
     * @return
     */
    public List<OSSObjectSummary> list();


    /**
     * 删除文件
     * @param fileKey
     * @return
     */
    public ResponseResult delete(String fileKey);


    /**
     * 下载文件
     * @param os
     * @param fileKey
     * @throws IOException
     */
    public void downFile(OutputStream os, String fileKey) throws IOException ;

    /**
     * 文件信息保存
     * @param req
     * @return
     */
    ResponseResult fileInfoSave(FileRelationReq req);
}