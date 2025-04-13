package io.github.eduardoconceicao90.libraryapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String paginateLogin() {
        return "login";
    }
}
