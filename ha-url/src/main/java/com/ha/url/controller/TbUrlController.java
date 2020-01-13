package com.ha.url.controller;

import com.ha.url.service.TbUrlService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author weirdo
 * @since 2020-01-13
 */
@RestController
@RequestMapping("/tbUrl/")
@Api(value = "/tbUrl/",description = "")
public class TbUrlController {

    @Autowired
    TbUrlService tbUrlService;


}
