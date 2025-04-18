package com.swe.backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("")
    public String Howdy() {
        return "Howdy Partner";
    }
}
