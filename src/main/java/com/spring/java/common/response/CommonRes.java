package com.spring.java.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonRes {
    public static ResponseEntity<?> Def(Object result){
        return ResponseEntity.ok(new ResponseMsg(result));
    }

    public static ResponseEntity<Object> Except( HttpStatus httpStatus, Object result){
        return ResponseEntity.status(httpStatus).body(result);
    }
}
