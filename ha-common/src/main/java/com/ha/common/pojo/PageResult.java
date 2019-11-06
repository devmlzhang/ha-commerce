package com.ha.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * <p>
 *    分页结果
 * </p>
 *
 */
@ApiModel(value = "分页结果")
public class PageResult<T> {


    @ApiModelProperty(value = "总条数",dataType = "Long")
    private Long total;

    @ApiModelProperty(value = "总页数",dataType = "Long")
    private Long totalPage;

    @ApiModelProperty(value = "当前页数据",dataType = "List")
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}