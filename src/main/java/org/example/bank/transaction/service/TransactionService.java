package org.example.bank.transaction.service;

import org.example.bank.account.entity.Account;
import org.example.bank.account.repository.AccountRepository;
import org.example.bank.common.DTOs;
import org.example.bank.transaction.entity.Transaction;
import org.example.bank.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public DTOs.AccountDTO createTransfer(DTOs.TransferRequest req) {
        if (req.amount == null || req.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        Account from = accountRepository.findById(req.fromAccountId).orElseThrow();
        Account to = accountRepository.findById(req.toAccountId).orElseThrow();

        // Partial implementation: simple debit + credit without concurrency controls
        from.setBalance(from.getBalance().subtract(req.amount));
        to.setBalance(to.getBalance().add(req.amount));

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction t = new Transaction(req.fromAccountId, req.toAccountId, req.amount, "TRANSFER", "Transfer");
        transactionRepository.save(t);

        return new DTOs.AccountDTO(to.getId(), to.getOwnerName(), to.getBalance());
    }
}

