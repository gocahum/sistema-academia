package com.gocahum.email.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDto {
    private String[] toUser;
    private String subject;
    private String message;
}