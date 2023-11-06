package com.dogstore.dogstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index(Model model) {
        return "index"; // index.html
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "index"; // index.html
    }

}
