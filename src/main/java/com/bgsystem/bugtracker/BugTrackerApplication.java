package com.bgsystem.bugtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class BugTrackerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BugTrackerApplication.class);
        app.run(args);
    }

}
