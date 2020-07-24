package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegControl {

    @GetMapping("/reg")
    public String index() {
        return "reg";
    }
}
