package com.example.boardcrud.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //Controller에서 예외 발생 시 여기서 처리함
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class) //CustomException이 발생하면 여기서
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(response, errorCode.getStatus());

    }

    @ExceptionHandler(Exception.class) //CustomException에서 만들지 못한 오류는 여기서
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Undefined Exception ", e);

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(response, errorCode.getStatus());
    }


}