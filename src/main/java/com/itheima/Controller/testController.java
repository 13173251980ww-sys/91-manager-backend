package com.itheima.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class testController {
    @GetMapping("/test")
    public Map<String, String> getname(){
        Map<String,String> name=new HashMap<>();
        name.put("name","小明");
        return name;
    }
}
