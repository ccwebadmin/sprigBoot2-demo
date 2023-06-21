package com.cc.sprigboot2demo.testController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cc
 * @date 2023/6/20-23:42
 */

@RestController
public class TestController {
    @RequestMapping("/w")
    public String world66(){
        return "World";
    }
}
