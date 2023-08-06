package com.spring.java.common.exception;

import com.spring.java.common.response.CommonRes;
import com.spring.java.common.response.ValidInfo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidInfo> errorList = ex.getBindingResult().getFieldErrors().stream().map( it ->
                ValidInfo.builder().field(it.getField()).msg(it.getDefaultMessage()).build()
        ).collect(Collectors.toList());
        return CommonRes.Except(HttpStatus.BAD_REQUEST, errorList);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidInfo> errorList = ex.getBindingResult().getFieldErrors().stream().map( it ->
                ValidInfo.builder().field(it.getField()).msg(it.getDefaultMessage()).build()
        ).collect(Collectors.toList());
        return CommonRes.Except(HttpStatus.BAD_REQUEST, errorList);
    }
}
