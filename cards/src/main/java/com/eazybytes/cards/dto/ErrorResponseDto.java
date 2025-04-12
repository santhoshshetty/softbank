package com.eazybytes.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;
    private HttpStatusCode statusCode;
    private String errorMessage;
    private LocalDateTime time;
}
