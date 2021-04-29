package com.gotta_watch_them_all.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
public class testController {

    @GetMapping
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok("SAlazzut mec");
    }
}
