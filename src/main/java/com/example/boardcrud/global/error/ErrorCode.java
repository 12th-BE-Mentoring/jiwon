package com.example.boardcrud.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found"),

    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "Username Already Exists"),

    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "Board Not Found"),

    INVALID_LOGIN(HttpStatus.UNAUTHORIZED, "Invalid User");

    private final HttpStatus status;
    private final String message;

}