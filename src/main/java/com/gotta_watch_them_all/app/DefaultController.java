package com.gotta_watch_them_all.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class DefaultController {

    @GetMapping
    public String test() {
        return "HOME PAGE";
    }
}
