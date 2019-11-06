package com.ha.common.utils;


import com.github.pagehelper.PageInfo;
import com.ha.common.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  分页工具
 * </p>
 *
 * @author ML.Zhang
 * @since 2018/12/13
 */
public class PageUtil {

    /**
     * <p>
     * 构造返回前段的分页数据
     * </p>
     *
     */
    public final static<T> PageVO<List<T>> getListPageResult(PageInfo pageInfo, List<T> model) {
        PageVO<List<T>> resultModel = new PageVO<List<T>>();
        resultModel.setCurrentPage(pageInfo.getPageNum());
        resultModel.setPageSize(pageInfo.getPageSize());
        resultModel.setPages(pageInfo.getPages());
        resultModel.setTotal(pageInfo.getTotal());
        resultModel.setList(model);
        return resultModel;
    }
    /**
     * <p>
     * 构造返回前段的分页数据
     * </p>
     *
     */
    public final static<T> PageVO<List<T>> getListPageResult(PageInfo<T> pageInfo) {
        return getListPageResult(pageInfo,pageInfo.getList());
    }

    /**
     * <p>
     * 使用分页插件
     * </p>
     *
     */
    public final static<T> PageInfo<T> getPageInfoResult(List<T> source,PageInfo pageInfo) {
        PageInfo<T> result = new PageInfo<>(source);
        result.setTotal(pageInfo.getTotal());
        result.setStartRow(pageInfo.getStartRow());
        result.setPageSize(pageInfo.getPageSize());
        result.setNextPage(pageInfo.getNextPage());
        result.setPrePage(pageInfo.getPrePage());
        return result;
    }
}
