package com.example.boardcrud.global.error;

public record ErrorResponse (
    String message,
    int status
) {
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getStatus().value());
    }
}