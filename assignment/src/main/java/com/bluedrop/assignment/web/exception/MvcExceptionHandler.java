package com.bluedrop.assignment.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class MvcExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle the validation exceptions.
     * @param ex
     * @return a response entity with a list of validation errors and a bad request status
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex){
        final Set<ConstraintViolation<?>> lstViolations = ex.getConstraintViolations();

        final List<String> errorsList = new ArrayList<>(lstViolations.size());
        lstViolations.forEach(error -> errorsList.add(error.toString()));

        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle the not found exceptions.
     * @param ex
     * @return a response entity with a JSON message and a bad request status
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectNotFoundException(final NotFoundException ex, final WebRequest webRequest) {
        final NotFoundExceptionResponse exceptionResponse = new NotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle the missing OrderDetails exception
     * @param ex
     * @return a response entity with a JSON message and a bad request status
     */
    @ExceptionHandler
    public final ResponseEntity<Object> handleMissingOrderDetailsException(final MissingOrderDetailsException ex, final WebRequest webRequest) {
        final MissingOrderDetailsResponse exceptionResponse = new MissingOrderDetailsResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}