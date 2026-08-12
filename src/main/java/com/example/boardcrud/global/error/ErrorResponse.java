package com.example.boardcrud.global.error;

public record ErrorResponse (
    String message,
    int status
) {
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getStatus().value()); //.value(): HttpStatus의 메서드. 상태 코드를 숫자로 반환
    }
}