package com.bgsystem.bugtracker.models.Test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {

    @GetMapping("a")
    public ResponseEntity<String> getTestResponse(){
        return ResponseEntity.ok(new String("Hello :D"));
    }

    @PostMapping("b")
    public ResponseEntity<String> postTestResponse(){
        return ResponseEntity.ok(new String("Hello post :D"));
    }

}
