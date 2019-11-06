package com.ha.page.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {




    @GetMapping(value = "/index")
    public String index(){
        return "page/index";
    }

    @GetMapping(value = "/echo")
    public String echo(){
        return "page/echo";
    }




}
