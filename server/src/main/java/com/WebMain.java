package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebMain.class, args);
        Server.run(args);
    }
}