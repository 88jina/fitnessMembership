package com.jina.portfolio.test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebMvcTest
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test done!";
    }
}
