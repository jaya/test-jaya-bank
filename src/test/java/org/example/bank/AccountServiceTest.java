package org.example.bank;

import org.example.bank.account.service.AccountService;
import org.example.bank.common.DTOs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    void openAccount_creates_account_and_records_initial_deposit() {
        DTOs.OpenAccountRequest req = new DTOs.OpenAccountRequest();
        req.ownerName = "Alice";
        req.initialDeposit = BigDecimal.valueOf(100);

        DTOs.AccountDTO dto = accountService.openAccount(req);

        assertThat(dto).isNotNull();
        assertThat(dto.ownerName).isEqualTo("Alice");
        assertThat(dto.balance).isEqualByComparingTo(BigDecimal.valueOf(100));
    }

    @Test
    void deposit_increases_balance() {
        DTOs.OpenAccountRequest req = new DTOs.OpenAccountRequest();
        req.ownerName = "Bob";
        req.initialDeposit = BigDecimal.ZERO;
        DTOs.AccountDTO dto = accountService.openAccount(req);

        DTOs.AccountDTO after = accountService.deposit(dto.id, BigDecimal.valueOf(50));
        assertThat(after.balance).isEqualByComparingTo(BigDecimal.valueOf(50));
    }
}

