package com.ha.search.bo;

import java.util.Map;

/**
 * <p>
 *    搜索业务对象
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/16
 */
public class SearchRequest {

    /**
     * 搜索条件
     */
    private String key;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 每页大小，不从页面接收，而是固定大小
    */
    private static final Integer DEFAULT_SIZE = 20;

    /**
     * 默认页
     */
    private static final Integer DEFAULT_PAGE = 1;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if (page == null){
            return DEFAULT_PAGE;
        }
        /**
         * 获取页码时做一些校验，不能小于1
         */
        return Math.max(DEFAULT_PAGE,page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }




    public Integer getDefaultSize() {
        return DEFAULT_SIZE;
    }
    @Override
    public String toString() {
        return "SearchRequest{" +
                "key='" + key + '\'' +
                ", page=" + page +
                '}';
    }
}
