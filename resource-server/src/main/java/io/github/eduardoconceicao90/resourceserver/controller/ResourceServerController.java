package io.github.eduardoconceicao90.resourceserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceServerController {

    @GetMapping("public")
    public ResponseEntity<String> publicEndpoint(){
        return ResponseEntity.ok("PUBLIC ENDPOINT!");
    }

    @GetMapping("private")
    public ResponseEntity<String> privateEndpoint(){
        return ResponseEntity.ok("PRIVATE ENDPOINT!");
    }

}
