package com.test.candidate.service.controller;

import com.test.candidate.service.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ExceptionHandlerAdvice
 *
 * @author Ben
 * @since 26/09/2015
 */
@ControllerAdvice
public class ControllerExceptionHandlerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public EntityNotFoundException entityNotFoundExceptionHandler(EntityNotFoundException e) {
        return e;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new FieldError(fieldError.getObjectName(), fieldError.getCode(), fieldError.getField(), String.valueOf(fieldError.getRejectedValue())))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(e.getClass().getTypeName(), e.getMessage(), fieldErrors);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception exceptionHandler(Exception e) {
        return e;
    }

    private class ErrorResponse {

        private final String type;

        private final String message;

        private final List<FieldError> fieldErrors;

        public ErrorResponse(String type, String message, List<FieldError> fieldErrors) {
            this.type = type;
            this.message = message;
            this.fieldErrors = fieldErrors;
        }

        public String getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }
    }

    private class FieldError {

        private final String resource;

        private final String field;

        private final String code;

        private final String rejectedValue;

        public FieldError(String resource, String field, String code, String rejectedValue) {
            this.resource = resource;
            this.field = field;
            this.code = code;
            this.rejectedValue = rejectedValue;
        }

        public String getResource() {
            return resource;
        }

        public String getField() {
            return field;
        }

        public String getCode() {
            return code;
        }

        public String getRejectedValue() {
            return rejectedValue;
        }
    }
}

