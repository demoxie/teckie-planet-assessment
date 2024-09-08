package com.teckieplanet.gradingapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class APIException extends RuntimeException{
    private final String message;
    private final Integer statusCode;
    private final String path;
    private final String method;
    private final String transactionId;
}
