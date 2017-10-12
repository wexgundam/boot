package org.mose.springboot.springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class FileUploadController {
    @GetMapping("/fileUpload")
    public String toFileUploadView() {
        return "fileUpload";
    }

    // Handling file upload request
    @PostMapping("/fileUpload")
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file)
            throws IOException {

        // Save file on system
        if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File("D:/Upload", file.getOriginalFilename())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } else {
            return new ResponseEntity<>("Invalid file.", HttpStatus.BAD_REQUEST);
        }

        String json = "{\"success\":true}";
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}