package com.example.file_ms;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping
    public String mongo(){
        return "this is mongo project";
    }
}
