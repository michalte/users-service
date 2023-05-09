package com.example.usersystem.service;

import com.example.usersystem.dto.UserDto;
import com.example.usersystem.repository.UserRepository;
import com.example.usersystem.util.UserDtoAndTransactionConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<UserDto> getAll() {
        return userRepository.findAll()
                .map(UserDtoAndTransactionConverter::toUserDto);
    }

    public Mono<UserDto> getUserById(final int userId) {
        return userRepository.findById(userId)
                .map(UserDtoAndTransactionConverter::toUserDto);
    }

    public Mono<UserDto> insertUser(Mono<UserDto> userDtoMono) {
        return userDtoMono
                .map(UserDtoAndTransactionConverter::toUser)
                .flatMap(userRepository::save)
                .map(UserDtoAndTransactionConverter::toUserDto);
    }

    public Mono<UserDto> updateUser(int userId, Mono<UserDto> userDtoMono) {
        return userRepository.findById(userId)
                .flatMap(user -> userDtoMono.map(UserDtoAndTransactionConverter::toUser)
                        .doOnNext(u -> u.setId(userId)))
                .flatMap(userRepository::save)
                .map(UserDtoAndTransactionConverter::toUserDto);
    }

    public Mono<Void> deleteUser(int userId) {
        return userRepository.deleteById(userId);
    }

}
