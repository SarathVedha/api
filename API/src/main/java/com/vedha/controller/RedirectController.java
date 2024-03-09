package com.vedha.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller()
@RequestMapping("/")
@AllArgsConstructor
public class RedirectController {

    @GetMapping("/")
    String swaggerRedirectGet() {

        return "redirect:/swagger-ui/index.html";
    }

    @PostMapping("/")
    String swaggerRedirectPost() {

        return "redirect:/swagger-ui/index.html";
    }

    @GetMapping("/db")
    String dbRedirectGet() {

        return "redirect:/h2-console";
    }

    @PostMapping("/db")
    String dbRedirectGetPost() {

        return "redirect:/h2-console";
    }

    @GetMapping("/monitor")
    String monitorRedirectGet() {

        return "redirect:/actuator";
    }

    @PostMapping("/monitor")
    String monitorRedirectGetPost() {

        return "redirect:/actuator";
    }
}
