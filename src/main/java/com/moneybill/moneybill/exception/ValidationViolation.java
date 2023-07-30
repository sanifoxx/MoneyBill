package com.moneybill.moneybill.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationViolation {

    private final String field;
    private final String message;
}
