package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bank;
import com.example.demo.service.BankService;

@RestController
//Combines @Controller and @ResponseBody to create RESTful web services
@RequestMapping("/bank")
//Base URL mapping for all endpoints in this controller

public class BankController {

    private final BankService bankService;
    
    //Automatically injects BankService dependency into this controller
    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    //End point for retrieving all banks
    @GetMapping("/list")
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return ResponseEntity.ok(banks);
    }

    //End point for retrieving a bank by its ID
    @GetMapping("/{bankId}")
    public ResponseEntity<Bank> getBankById(@PathVariable int bankId) {
        try {
            Bank bank = bankService.getBankById(bankId);
            return ResponseEntity.ok(bank);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //End point for creating a new bank
    @PostMapping("/add")
    public ResponseEntity<String> addBank(@RequestBody Bank bank) {
        try {
            bankService.addBank(bank);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bank added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //End point for updating an existing bank
    @PutMapping("/update")
    public ResponseEntity<String> updateBank(@RequestBody Bank bank) {
        try {
            bankService.updateBank(bank);
            return ResponseEntity.ok("Bank updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //End point for deleting a bank by its ID
    @DeleteMapping("/delete/{bankId}")
    public ResponseEntity<String> deleteBank(@PathVariable int bankId) {
        try {
            bankService.deleteBank(bankId);
            return ResponseEntity.ok("Bank deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
