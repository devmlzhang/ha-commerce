package com.ha.goods.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * <p>
 *   测试
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/3
 */
@RequestMapping("category")
public interface TestCategoryApi {

    /**
     * 根据id，查询分类名称
     * @param ids
     * @return
     */
    @GetMapping("names")
    ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids") List<Long> ids);

}
