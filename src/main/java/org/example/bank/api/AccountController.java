package org.example.bank.api;

import org.example.bank.account.service.AccountService;
import org.example.bank.common.DTOs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<DTOs.AccountDTO> openAccount(@RequestBody DTOs.OpenAccountRequest req) {
        DTOs.AccountDTO dto = accountService.openAccount(req);
        return ResponseEntity.created(URI.create("/accounts/" + dto.id)).body(dto);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<DTOs.AccountDTO> deposit(@PathVariable Long id, @RequestBody DTOs.DepositRequest req) {
        DTOs.AccountDTO dto = accountService.deposit(id, req.amount);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<DTOs.AccountDTO> withdraw(@PathVariable Long id, @RequestBody DTOs.WithdrawRequest req) {
        DTOs.AccountDTO dto = accountService.withdraw(id, req.amount);
        return ResponseEntity.ok(dto);
    }
}

