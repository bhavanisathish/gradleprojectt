package com.example.allinone.utilis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtilis {

    public ResponseEntity getTemplate(int code,String message){
        return ResponseEntity.status(code).body(message);
    }


}
