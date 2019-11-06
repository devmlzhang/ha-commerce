
package com.ha.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *    分页请求参数
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/9
 */
@ApiModel(value = "请求分页参数")
public class PageParam {

    @ApiModelProperty(value = "当前页",dataType = "int")
    int pageNum;
    @ApiModelProperty(value = "当前页要显示记录数",dataType = "int")
    int pageSize;

    public PageParam(){}

    public PageParam(Integer pageNum, Integer pageSize) {
        if(pageNum==null || pageNum == 0){
            pageNum=1;
        }
        if(pageSize==null || pageSize == 0) {
            pageSize=10;
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
