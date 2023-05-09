package com.example.usersystem.util;

import com.example.usersystem.dto.TransactionRequestDto;
import com.example.usersystem.dto.TransactionResponseDto;
import com.example.usersystem.dto.TransactionStatus;
import com.example.usersystem.dto.UserDto;
import com.example.usersystem.entity.User;
import com.example.usersystem.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class UserDtoAndTransactionConverter {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public static UserTransaction toUserTransaction(TransactionRequestDto requestDto) {
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setUserId(requestDto.getUserId());
        userTransaction.setAmount(requestDto.getAmount());
        userTransaction.setTransactionDate(LocalDateTime.now());
        return userTransaction;
    }

    public static TransactionResponseDto toTransactionResponseDto(TransactionRequestDto transactionRequestDto, TransactionStatus transactionStatus) {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setUserId(transactionRequestDto.getUserId());
        transactionResponseDto.setAmount(transactionRequestDto.getAmount());
        transactionResponseDto.setStatus(transactionStatus);
        return transactionResponseDto;
    }

}
