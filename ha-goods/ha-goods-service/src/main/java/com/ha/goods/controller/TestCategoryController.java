package com.ha.goods.controller;


import com.ha.common.response.ResponseResult;
import com.ha.goods.client.UserClient;
import com.ha.goods.pojo.Category;
import com.ha.goods.service.CategoryService;
import com.ha.user.pojo.UserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("category")
public class TestCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserClient userClient;

    /**
     * 根据父节点查询商品类目
     * @param pid
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('role:add')")
    public ResponseResult<List<Category>> queryCategoryByPid(@RequestParam("pid") Long pid){
        List<Category> categories = categoryService.queryCategoryByPid(pid);
        return ResponseResult.successResult(categories);
    }


    @GetMapping("/test")
    public ResponseResult<UserExt> queryTest(){
        String weirdo = userClient.getUserName("weirdo");
        return ResponseResult.successResult(weirdo);
    }






}
