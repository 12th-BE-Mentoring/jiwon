package com.example.boardcrud.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

// 모든 필드를 받는 생성자 생성
@AllArgsConstructor
public class BoardUpdateRequest {

    // 공백, 빈 문자열 안됨
    @NotBlank
    private String title;

    // 공백O, null X
    @NotNull
    private String content;

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}

