package com.gocahum.email.controller;

import com.gocahum.email.dto.EmailDto;
import com.gocahum.email.dto.EmailFileDto;
import com.gocahum.email.service.IEmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/v1")
@Log4j2
public class Controller {
    @Autowired
    private IEmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<?> receiveEmail(@RequestBody EmailDto emailDto) {
        log.info("Mensaje recibido:");
        log.info(emailDto.toString());
        //emailService.sendEmail(emailDto.getToUser(), emailDto.getSubject(), emailDto.getMessage());
        return (ResponseEntity<?>) ResponseEntity.ok("mensaje enviado");
    }

    @GetMapping("/demo")
    public ResponseEntity<?> GetSecurity() {
        log.info("Mensaje recibido:");

        //emailService.sendEmail(emailDto.getToUser(), emailDto.getSubject(), emailDto.getMessage());
        return (ResponseEntity<?>) ResponseEntity.ok("mensaje enviado");
    }

    @PostMapping("email-file")
    public ResponseEntity<?> receiveEmailWithfile(@ModelAttribute  EmailFileDto emailFileDto){
        try {
            String fileName = emailFileDto.getFile().getOriginalFilename();
            Path path = Paths.get("src/main/resources/files/"+fileName);
            Files.createDirectories(path.getParent());
            Files.copy(emailFileDto.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File file = path.toFile();
            emailService.sendEmailWithFile(emailFileDto.getToUser(), emailFileDto.getSubject(), emailFileDto.getMessage(), file);
            //Files.deleteIfExists(path.getParent());
            return (ResponseEntity<?>) ResponseEntity.ok("Email enviado correctamente");
        }catch (IOException ex){
            log.error("Error",ex);
            return new ResponseEntity<>("No se pudo enviar el emial",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
