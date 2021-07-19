package com.golaxy.machine.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Description: 在线访问api
* @Params:
* @Return:
* @Author: miaoxuebing
* @Date: 2021/2/3 下午5:54
**/
@Controller
@RequestMapping("")
public class SwaggerRestController {

    @GetMapping("")
    public String index(){
        return "redirect:/swagger-ui/index.html";
    }
}
