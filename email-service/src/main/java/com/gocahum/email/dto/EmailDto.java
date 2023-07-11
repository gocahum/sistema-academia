package com.gocahum.email.dto;

import lombok.*;

import java.util.Arrays;

@Data
@NoArgsConstructor
@ToString
public class EmailDto {
    private String[] toUser;
    private String subject;
    private String message;

}