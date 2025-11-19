package org.example.bank.api;

import org.example.bank.common.DTOs;
import org.example.bank.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    private final TransactionService transactionService;

    public TransferController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<DTOs.AccountDTO> transfer(@RequestBody DTOs.TransferRequest req) {
        DTOs.AccountDTO dto = transactionService.createTransfer(req);
        return ResponseEntity.ok(dto);
    }
}

