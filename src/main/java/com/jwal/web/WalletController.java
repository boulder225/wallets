package com.jwal.web;

import com.jwal.model.Account;
import com.jwal.model.MutationRequest;
import com.jwal.model.MutationResponse;
import com.jwal.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Validated
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/credits")
    public ResponseEntity<MutationResponse> credit(@Valid @RequestBody MutationRequest request) {
        return ResponseEntity
                .ok().body(walletService.credit(request));
    }

    @PostMapping("/debits")
    public ResponseEntity<MutationResponse> debit(@Valid @RequestBody MutationRequest request) {
        return ResponseEntity
                .ok().body(walletService.debit(request));
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(
            @RequestHeader(value = "X-Idempotency-Key", required = false) String xIdempotencyKey,
            @Valid @RequestBody String name) {
        Account account = walletService.create(name);
        URI location = URI.create("/accounts/" + account.id());
        return ResponseEntity
                .created(location)
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .header("X-Idempotency-Key", xIdempotencyKey == null ? "" : xIdempotencyKey)
                .body(account);
    }

}
