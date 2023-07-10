package com.gocahum.email.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailFileDto {
    private String[] toUser;
    private String subject;
    private String message;
    @ToString.Exclude
    private MultipartFile file;
}


