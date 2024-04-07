package com.naham.api.demo.controller;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AdminController {

    @GetMapping("/admin")
    public String admin(@PathParam("id") Long id) {
        return testAdmin();
    }

/*    @GetMapping("/hello/../admin")
    public String admin2() {
        return testAdmin();
    }*/

    private String testAdmin() {
        log.info("Get admin info");
        return "Fucking shit!";
    }

}