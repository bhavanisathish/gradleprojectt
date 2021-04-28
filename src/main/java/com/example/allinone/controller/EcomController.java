package com.example.allinone.controller;

import com.example.allinone.model.EcomModel;
import com.example.allinone.model.EcomReq;
import com.example.allinone.service.EcomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EcomController {

    @Autowired
    private EcomService ecomService;

    @PostMapping("/post-details")
    public ResponseEntity createUpdate(@RequestBody EcomModel ecomModel){
        return ecomService.createUpdate(ecomModel);
    }

    @PostMapping("/get")
    public ResponseEntity get(@RequestBody EcomReq ecomReq){
        return ecomService.get(ecomReq);
    }
}
