package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Bank;
import com.example.demo.model.User;
import com.example.demo.repository.BankRepository;

class BankServiceTest {

    @InjectMocks
    private BankService bankService;

    @Mock
    private BankRepository bankRepository;

    private Bank bank;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        User user = new User();  // Assume User is another entity class
        bank = new Bank(1, "HDFC Bank", "HDFC0000123", "123456789", "user@upi", "savings", 5000.0, user);
    }

    @Test
    void testGetBankById_Success() {
        when(bankRepository.findById(1)).thenReturn(Optional.of(bank));
        
        Bank foundBank = bankService.getBankById(1);
        
        assertEquals(bank, foundBank);
    }

    @Test
    void testGetBankById_NotFound() {
        when(bankRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> bankService.getBankById(2));
    }

    @Test
    void testGetAllBanks() {
        List<Bank> banks = Arrays.asList(bank);
        when(bankRepository.findAll()).thenReturn(banks);

        List<Bank> allBanks = bankService.getAllBanks();
        assertEquals(1, allBanks.size());
        assertEquals(bank, allBanks.get(0));
    }

    @Test
    void testAddBank_ValidBank() {
        bankService.addBank(bank);
    }

    @Test
    void testAddBank_InvalidBank() {
        Bank invalidBank = new Bank();
        assertThrows(IllegalArgumentException.class, () -> bankService.addBank(invalidBank));
    }

    @Test
    void testDeleteBank_Success() {
        when(bankRepository.existsById(1)).thenReturn(true);
        
        bankService.deleteBank(1);
    }

    @Test
    void testDeleteBank_NotFound() {
        when(bankRepository.existsById(2)).thenReturn(false);
        
        assertThrows(IllegalArgumentException.class, () -> bankService.deleteBank(2));
    }
}
