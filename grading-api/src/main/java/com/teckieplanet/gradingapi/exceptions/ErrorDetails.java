package com.teckieplanet.gradingapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String error;
    private String message;
    private String details;
    private String path;
    private String timestamp;
    private String method;
    private String transactionId;
    private Integer statusCode;
}
