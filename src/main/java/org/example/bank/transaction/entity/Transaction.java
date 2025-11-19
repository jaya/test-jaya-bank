package org.example.bank.transaction.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromAccountId;
    private Long toAccountId;

    @Column(nullable = false)
    private BigDecimal amount;

    private String type;

    private Instant timestamp;

    private String description;

    public Transaction() {}

    public Transaction(Long fromAccountId, Long toAccountId, BigDecimal amount, String type, String description) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.timestamp = Instant.now();
    }

    public Long getId() { return id; }
    public Long getFromAccountId() { return fromAccountId; }
    public Long getToAccountId() { return toAccountId; }
    public BigDecimal getAmount() { return amount; }
    public String getType() { return type; }
    public Instant getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
}

