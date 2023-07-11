package com.gocahum.email.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocahum.email.dto.EmailDto;
import com.gocahum.email.service.EmailServiceImpl;
import com.gocahum.email.service.IEmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

@Configuration
@Log4j2
public class ConsumerKafkaListener {
    @Autowired
    private IEmailService emailService;

    @KafkaListener(topics = {"uady-mail-topic"}, groupId = "test-consumer-group")
    private void listener(String message){
        log.info(message);
        EmailDto emailDto = null;
        try {
            emailDto = new ObjectMapper().readValue(message, EmailDto.class);
            log.info(emailDto.toString());
        } catch (JsonProcessingException e) {
            //throw new RuntimeException(e);
            log.info(e.getMessage());
        }

        emailService.sendEmail(emailDto.getToUser(), emailDto.getSubject(), emailDto.getMessage());

    }

}
