package com.teckieplanet.gradingapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse <D>{
    private D data;
    private String message;
    private Integer status;
    private String transactionId;
}
