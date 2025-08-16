package com.example.boardpractice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // templates/signup.html
    }
} //새로만든컨트롤러