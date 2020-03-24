package com.huobi.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {

    /**
     * 路由 /index
     * 返回 index 这里默认配置自动映射到 templages/index
     * */
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("welcome","hello 李厚京");
        return "index";
    }
}
