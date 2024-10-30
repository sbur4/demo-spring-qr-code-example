package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.util.QRCodeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping(path = "/qr/{text}", produces = "image/png")
    public ResponseEntity<byte[]> getQRCode(@PathVariable(value = "text") String text,
                                            @RequestParam(value = "width", defaultValue = "200") int width,
                                            @RequestParam(value = "height", defaultValue = "200") int height) {
        try {
            byte[] image = QRCodeUtils.generateQRCodeImage(text, width, height);
            return ResponseEntity.status(HttpStatus.OK).body(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}