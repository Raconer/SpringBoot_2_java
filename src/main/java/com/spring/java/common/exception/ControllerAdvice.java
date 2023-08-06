package com.spring.java.common.exception;

import com.spring.java.common.response.CommonRes;
import com.spring.java.common.response.ValidInfo;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    // 예외 처리 메서드 작성
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        logger.error("SQLIntegrityConstraintViolationException occurred: {}", ex.getMessage());
        return CommonRes.Except(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

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
