package com.bluedrop.assignment.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class MvcExceptionHandler {

    /**
     * Handle the validation exceptions.
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex){
        final Set<ConstraintViolation<?>> lstViolations = ex.getConstraintViolations();

        final List<String> errorsList = new ArrayList<>(lstViolations.size());
        lstViolations.forEach(error -> errorsList.add(error.toString()));

        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

}