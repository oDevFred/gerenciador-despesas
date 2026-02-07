package io.github.com.odevfred.gerenciador_despesas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/view-expenses")
    public String showPage() {
        return "index";
    }
}
