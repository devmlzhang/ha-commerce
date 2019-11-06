package com.ha.file.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "file_relation")
public class FileRelation {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 业务表关联id
     */
    @Column(name = "rel_id")
    private String relId;

    /**
     * 文件id
     */
    @Column(name = "file_id")
    private String fileId;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件扩展名
     */
    @Column(name = "file_ext")
    private String fileExt;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态(1:有效;0:无效)
     */
    private Integer status;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private String fileSize;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取业务表关联id
     *
     * @return rel_id - 业务表关联id
     */
    public String getRelId() {
        return relId;
    }

    /**
     * 设置业务表关联id
     *
     * @param relId 业务表关联id
     */
    public void setRelId(String relId) {
        this.relId = relId;
    }

    /**
     * 获取文件id
     *
     * @return file_id - 文件id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置文件id
     *
     * @param fileId 文件id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取文件类型
     *
     * @return type - 文件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置文件类型
     *
     * @param type 文件类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件扩展名
     *
     * @return file_ext - 文件扩展名
     */
    public String getFileExt() {
        return fileExt;
    }

    /**
     * 设置文件扩展名
     *
     * @param fileExt 文件扩展名
     */
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取状态(1:有效;0:无效)
     *
     * @return status - 状态(1:有效;0:无效)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(1:有效;0:无效)
     *
     * @param status 状态(1:有效;0:无效)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取文件大小
     *
     * @return file_size - 文件大小
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     *
     * @param fileSize 文件大小
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}