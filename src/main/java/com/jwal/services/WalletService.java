package com.jwal.services;

import com.jwal.model.Account;
import com.jwal.model.MutationRequest;
import com.jwal.model.MutationResponse;

public interface WalletService {
    Account create(String name);
    Account getBalance(String accountId);
    MutationResponse credit(MutationRequest request);
    MutationResponse debit(MutationRequest request);
}
