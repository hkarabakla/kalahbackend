package com.hkarabakla.kalahbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String main() {
        return "redirect:/swagger-ui.html";
    }
}
