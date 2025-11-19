package org.example.bank.account.service;

import org.example.bank.account.entity.Account;
import org.example.bank.account.repository.AccountRepository;
import org.example.bank.common.DTOs;
import org.example.bank.common.Exceptions;
import org.example.bank.transaction.entity.Transaction;
import org.example.bank.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public DTOs.AccountDTO openAccount(DTOs.OpenAccountRequest req) {
        if (req.ownerName == null || req.ownerName.isEmpty()) {
            throw new IllegalArgumentException("ownerName is required");
        }
        BigDecimal initial = req.initialDeposit == null ? BigDecimal.ZERO : req.initialDeposit;
        Account acc = new Account(req.ownerName, initial);
        accountRepository.save(acc);
        if (initial.compareTo(BigDecimal.ZERO) > 0) {
            Transaction t = new Transaction(null, acc.getId(), initial, "DEPOSIT", "Initial deposit");
            transactionRepository.save(t);
        }
        return new DTOs.AccountDTO(acc.getId(), acc.getOwnerName(), acc.getBalance());
    }

    @Transactional
    public DTOs.AccountDTO deposit(Long accountId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        Account acc = accountRepository.findById(accountId).orElseThrow(() -> new Exceptions.NotFoundException("Account not found"));
        acc.setBalance(acc.getBalance().add(amount));
        accountRepository.save(acc);
        Transaction t = new Transaction(null, acc.getId(), amount, "DEPOSIT", "Deposit");
        transactionRepository.save(t);
        return new DTOs.AccountDTO(acc.getId(), acc.getOwnerName(), acc.getBalance());
    }

    @Transactional
    public DTOs.AccountDTO withdraw(Long accountId, BigDecimal amount) {
        // TODO implement withdraw logic
        throw new UnsupportedOperationException("Withdraw not implemented");
    }

    public Account getAccountEntity(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new Exceptions.NotFoundException("Account not found"));
    }
}

