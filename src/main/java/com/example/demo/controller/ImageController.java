package com.example.demo.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/img")
@CrossOrigin(origins = "*")
public class ImageController {
    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        Resource resource = new ClassPathResource("img/" + imageName);
        byte[] imageBytes = resource.getInputStream().readAllBytes();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
    @GetMapping("png/{imageName}")
    public ResponseEntity<byte[]> getImagePng(@PathVariable String imageName) throws IOException {
        Resource resource = new ClassPathResource("img/" + imageName);
        byte[] imageBytes = resource.getInputStream().readAllBytes();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }
}
