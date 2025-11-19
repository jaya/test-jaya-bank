package org.example.bank;

import org.example.bank.common.DTOs;
import org.example.bank.common.Exceptions;
import org.example.bank.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class WithdrawServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    void withdraw_decreases_balance_and_records_transaction() {
        DTOs.OpenAccountRequest req = new DTOs.OpenAccountRequest();
        req.ownerName = "Eve";
        req.initialDeposit = BigDecimal.valueOf(100);
        DTOs.AccountDTO dto = accountService.openAccount(req);

        // Candidate should implement withdraw so this test passes.
        // Expected behavior: after withdrawal 40, balance is 60 and a transaction of type WITHDRAW exists.
        // Current implementation throws UnsupportedOperationException to signal the task.
        assertThrows(UnsupportedOperationException.class, () -> accountService.withdraw(dto.id, BigDecimal.valueOf(40)), "Implement withdraw: ensure balance decreases and transaction recorded");
    }

    @Test
    void withdraw_fails_when_insufficient_funds() {
        DTOs.OpenAccountRequest req = new DTOs.OpenAccountRequest();
        req.ownerName = "Frank";
        req.initialDeposit = BigDecimal.valueOf(10);
        DTOs.AccountDTO dto = accountService.openAccount(req);

        // Expected: InsufficientFundsException should be thrown. Candidate should implement this behavior.
        assertThrows(Exceptions.InsufficientFundsException.class, () -> accountService.withdraw(dto.id, BigDecimal.valueOf(50)));
    }
}

