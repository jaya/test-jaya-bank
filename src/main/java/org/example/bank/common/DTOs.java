package org.example.bank.common;

// Simple holder for DTO classes (kept in one file for brevity in the test project)
import java.math.BigDecimal;

public class DTOs {
    public static class OpenAccountRequest {
        public String ownerName;
        public BigDecimal initialDeposit;
    }

    public static class AccountDTO {
        public Long id;
        public String ownerName;
        public BigDecimal balance;

        public AccountDTO() {}

        public AccountDTO(Long id, String ownerName, BigDecimal balance) {
            this.id = id;
            this.ownerName = ownerName;
            this.balance = balance;
        }
    }

    public static class DepositRequest {
        public BigDecimal amount;
    }

    public static class WithdrawRequest {
        public BigDecimal amount;
    }

    public static class TransferRequest {
        public Long fromAccountId;
        public Long toAccountId;
        public BigDecimal amount;
    }
}

