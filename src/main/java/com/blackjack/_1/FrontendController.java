package com.blackjack._1;

import org.springframework.web.bind.annotation.GetMapping;

public class FrontendController {
    @GetMapping(value = "/{path:[^\\.]*}")
    public String forward() {
        // Forward to the React frontend
        return "forward:/index.html";
    }
}
