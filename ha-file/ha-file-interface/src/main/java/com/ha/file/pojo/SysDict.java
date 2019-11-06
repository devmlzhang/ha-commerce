package com.ha.file.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Table(name = "sys_dict")
@Data
@ApiModel(value = "")
public class SysDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private String id;


    @ApiModelProperty(value = "属性名称")
    private String name;


    @ApiModelProperty(value = "属性值")
    private String value;

    @ApiModelProperty(value = "code值")
    private String code;

    @ApiModelProperty(value = "字典描述")
    private String description;


    @ApiModelProperty(value = "组别")
    private String groups;


    @ApiModelProperty(value = "排序")
    private Integer sort;


    @Column(name = "p_id")
    @ApiModelProperty(value = "父级节点")
    private String pId;



}