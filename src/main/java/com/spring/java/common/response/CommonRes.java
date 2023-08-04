package com.spring.java.common.response;

import org.springframework.http.ResponseEntity;

public class CommonRes {
    public static ResponseEntity<?> Def(Object result){
        return ResponseEntity.ok(new ResponseMsg(result));
    }
}
