package br.edu.iff.ccc.webproject.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/")
public class MainRestViewController {

    @GetMapping("/status")
    public ResponseEntity getStatus() {
        return ResponseEntity.ok("API is running");
    }
    

}
