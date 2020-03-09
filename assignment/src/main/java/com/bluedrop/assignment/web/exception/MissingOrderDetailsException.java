package com.bluedrop.assignment.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MissingOrderDetailsException extends RuntimeException {
    private String message;
}
