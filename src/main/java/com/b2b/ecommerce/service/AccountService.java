package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.AccountDTO;

public interface AccountService {
    long register(AccountDTO accountDTO);
    String login(String mobileNumber, String password);
    String logout();
    String updateAccount(long accountId, AccountDTO accountDTO);
    AccountDTO getAccountDetails(long accountId);
}
