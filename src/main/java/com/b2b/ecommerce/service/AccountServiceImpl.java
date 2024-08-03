package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.AccountDTO;
import com.b2b.ecommerce.entity.Account;
import com.b2b.ecommerce.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public long register(AccountDTO accountDTO) {
        Account account = new Account();
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setMobileNumber(accountDTO.getMobileNumber());
        account.setEmailId(accountDTO.getEmailId());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        accountRepository.save(account);
        return account.getId();
    }

    @Override
    public String login(String mobileNumber, String password) {
        Account account = accountRepository.findByMobileNumber(mobileNumber);
        if (account != null && passwordEncoder.matches(password, account.getPassword())) {
            return "Login successful!";
        }
        return "Invalid credentials!";
    }

    @Override
    public String logout() {
        // Implement logout logic (e.g., invalidate session or token)
        return "Logout successful!";
    }

    @Override
    public String updateAccount(long accountId, AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setMobileNumber(accountDTO.getMobileNumber());
        account.setEmailId(accountDTO.getEmailId());
        accountRepository.save(account);
        return "Account updated successfully!";
    }

    @Override
    public AccountDTO getAccountDetails(long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setFirstName(account.getFirstName());
        accountDTO.setLastName(account.getLastName());
        accountDTO.setMobileNumber(account.getMobileNumber());
        accountDTO.setEmailId(account.getEmailId());
        return accountDTO;
    }
}
