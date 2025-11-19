package org.example.bank;

import org.example.bank.common.DTOs;
import org.example.bank.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private org.example.bank.account.service.AccountService accountService;

    @Test
    void transfer_moves_funds_between_accounts() {
        DTOs.OpenAccountRequest a1 = new DTOs.OpenAccountRequest();
        a1.ownerName = "Carol";
        a1.initialDeposit = BigDecimal.valueOf(200);
        DTOs.AccountDTO src = accountService.openAccount(a1);

        DTOs.OpenAccountRequest a2 = new DTOs.OpenAccountRequest();
        a2.ownerName = "Dave";
        a2.initialDeposit = BigDecimal.ZERO;
        DTOs.AccountDTO dest = accountService.openAccount(a2);

        DTOs.TransferRequest req = new DTOs.TransferRequest();
        req.fromAccountId = src.id;
        req.toAccountId = dest.id;
        req.amount = BigDecimal.valueOf(75);

        DTOs.AccountDTO afterDest = transactionService.createTransfer(req);
        assertThat(afterDest.balance).isEqualByComparingTo(BigDecimal.valueOf(75));
    }
}

