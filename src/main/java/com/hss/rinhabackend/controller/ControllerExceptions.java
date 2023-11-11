package com.hss.rinhabackend.controller;

import com.hss.rinhabackend.dtos.RinhaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptionMethod(Exception ex, WebRequest request) {

        var exception = new RinhaException();

        if(ex instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            List<String> listError = fieldErrors.stream().map(FieldError::getDefaultMessage).toList();
            exception.setMessage(listError);
        } else {
            exception.setMessage(singletonList(ex.getLocalizedMessage()));
        }

        exception.setError(ex.getClass().getCanonicalName());
        exception.setPath(((ServletWebRequest) request).getRequest().getServletPath());

        return new ResponseEntity<>(exception, new HttpHeaders(), BAD_REQUEST);
    }
}
