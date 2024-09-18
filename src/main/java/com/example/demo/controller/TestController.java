package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/runPayment")
    public String runPayment() {
        paymentService.makePayment();
        return "Payment process executed";
    }
}

