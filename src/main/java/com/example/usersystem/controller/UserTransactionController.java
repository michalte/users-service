package com.example.usersystem.controller;

import com.example.usersystem.dto.TransactionRequestDto;
import com.example.usersystem.dto.TransactionResponseDto;
import com.example.usersystem.entity.UserTransaction;
import com.example.usersystem.service.UserTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
@RequiredArgsConstructor
public class UserTransactionController {

    private final UserTransactionService userTransactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> transactionRequestDtoMono) {
        return transactionRequestDtoMono.flatMap(userTransactionService::createTransaction);
    }

    @GetMapping()
    public Flux<UserTransaction> getUserTransactions(@RequestParam int id) {
        return userTransactionService.getUserTransactions(id);
    }

}
