package com.swe.backend.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.swe.backend.Entity.User;
import com.swe.backend.Service.UserService;
import com.swe.backend.Views.Views;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class Hello {

    @GetMapping("")
    public String Howdy() {
        return "Howdy Partner";
    }
}
