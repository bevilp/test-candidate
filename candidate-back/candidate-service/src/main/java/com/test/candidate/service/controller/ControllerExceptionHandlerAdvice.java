package com.test.candidate.service.controller;

import com.test.candidate.service.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * <p>
 * All exception handling advices for controllers
 *
 * @author Ben
 * @since 26/09/2015
 */
@ControllerAdvice
public class ControllerExceptionHandlerAdvice {

    private static final Logger LOG = LoggerFactory
            .getLogger(ControllerExceptionHandlerAdvice.class);

    /**
     * Handle all EntityNotFoundException exceptions.
     * <p>
     * Does not do anything apart from returning a HTTP 404 error
     *
     * @param e
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public EntityNotFoundException entityNotFoundExceptionHandler(EntityNotFoundException e) {
        LOG.debug("Could not find entity", e);
        return e;
    }

    /**
     * Handle all controller input validation errors
     * <p>
     * will return a HTTP 400 with a response body containing a {@link com.test.candidate.service.controller.ControllerExceptionHandlerAdvice.ErrorResponse}
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // Could have lots of these errors. No need to log them at another level than debug.
        LOG.debug("invalid argument", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new FieldError.Builder()
                        .code(fieldError.getCode())
                        .field(fieldError.getField())
                        .rejectedValue(String.valueOf(fieldError.getRejectedValue()))
                        .resource(fieldError.getObjectName())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(e.getClass().getTypeName(), e.getMessage(), fieldErrors);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception exceptionHandler(Exception e) {
        // unexpected error!
        LOG.error("", e);
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

    private static class FieldError {

        static class Builder {

            private String resource;

            private String field;

            private String code;

            private String rejectedValue;

            public Builder resource(String resource) {
                this.resource = resource;
                return this;
            }

            public Builder field(String field) {
                this.field = field;
                return this;
            }

            public Builder code(String code) {
                this.code = code;
                return this;
            }

            public Builder rejectedValue(String rejectedValue) {
                this.rejectedValue = rejectedValue;
                return this;
            }

            public FieldError build() {
                return new FieldError(resource, field, code, rejectedValue);
            }
        }

        private final String resource;

        private final String field;

        private final String code;

        private final String rejectedValue;

        private FieldError(String resource, String field, String code, String rejectedValue) {
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

