package ru.alexeymarino.springlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PeopleController {

    @GetMapping("/people")
    public String showAll() {
        return null;
    }
}
