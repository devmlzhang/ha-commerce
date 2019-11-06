package com.ha.search.client;

import com.ha.goods.api.TestCategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 *    商品FeignClient
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/16
 */
@FeignClient(value = "ha-goods")
public interface CategorClient extends TestCategoryApi {
}
