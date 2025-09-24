package com.jwal.services.impl;

import com.jwal.model.Account;
import com.jwal.model.MutationRequest;
import com.jwal.model.MutationResponse;
import com.jwal.services.WalletService;
import com.jwal.web.ErrorHandler;
import com.jwal.web.ErrorHandler.ConflictException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryWalletService implements WalletService {
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public Account create(String name) {
        String id = UUID.randomUUID().toString();
        Account created = new Account(id, name, BigInteger.ZERO);
        Account existing = accounts.putIfAbsent(id, created);
        if (existing != null) {
            throw new ConflictException("Account with id " + id + " already exists");
        }
        return created;
    }

    @Override
    public Account getBalance(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new ErrorHandler.NotFoundException("Account with id " + accountId + " not found");
        }
        return account;
    }

    @Override
    public MutationResponse credit(MutationRequest request) {
        return null;
    }

    @Override
    public MutationResponse debit(MutationRequest request) {
        return null;
    }
}
