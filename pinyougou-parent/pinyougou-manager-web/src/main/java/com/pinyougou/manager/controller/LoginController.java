package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/****
 *author:Lenovo 19:58
 *description:深圳黑马
 *version:1.0

 ****/

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/name")
    public Map name(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,String> map=new HashMap<String,String>();
        map.put("loginName",name);
        return map;
    }

}
