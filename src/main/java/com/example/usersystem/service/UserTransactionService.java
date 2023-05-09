package com.example.usersystem.service;

import com.example.usersystem.dto.TransactionRequestDto;
import com.example.usersystem.dto.TransactionResponseDto;
import com.example.usersystem.dto.TransactionStatus;
import com.example.usersystem.entity.UserTransaction;
import com.example.usersystem.repository.UserRepository;
import com.example.usersystem.repository.UserTransactionRepository;
import com.example.usersystem.util.UserDtoAndTransactionConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserTransactionService {

    private final UserRepository userRepository;

    private final UserTransactionRepository userTransactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto) {
        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> UserDtoAndTransactionConverter.toUserTransaction(requestDto))
                .flatMap(userTransactionRepository::save)
                .map(t -> UserDtoAndTransactionConverter.toTransactionResponseDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(UserDtoAndTransactionConverter.toTransactionResponseDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getUserTransactions(int id) {
        return userTransactionRepository.findUserTransactionByUserId(id);
    }


}
