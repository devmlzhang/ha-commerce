package com.ha.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 分页
 * </p>
 *
 * @author ML.Zhang
 * @since 2018/12/13
 */
@ApiModel
@Data
public class PageVO<T>   {

    @ApiModelProperty("总条数")
    private Long total;
    @ApiModelProperty("总页数")
    private Integer pages;
    @ApiModelProperty(value = "数据结果")
    private T list;
    @ApiModelProperty(value = "当前第几页")
    private Integer currentPage;
    @ApiModelProperty(value = "每页显示多少条")
    private Integer pageSize;

}
