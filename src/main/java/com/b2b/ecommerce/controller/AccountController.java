package com.b2b.ecommerce.controller;

import com.b2b.ecommerce.dto.AccountDTO;
import com.b2b.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String mobileNumber, @RequestParam String password) {
        String status = accountService.login(mobileNumber, password);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Assuming logout logic is handled here
        String status = accountService.logout();
        return ResponseEntity.ok(status);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody AccountDTO accountDTO) {
        Long accountId = accountService.register(accountDTO);
        return ResponseEntity.ok(accountId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        String status = accountService.updateAccount(id, accountDTO);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.getAccountDetails(id);
        return ResponseEntity.ok(accountDTO);
    }
}
