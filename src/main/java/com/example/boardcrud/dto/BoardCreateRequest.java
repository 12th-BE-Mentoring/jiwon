package com.example.boardcrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

// 모든 필드를 넣는 생성자
@AllArgsConstructor
public class BoardCreateRequest {

    // 공백,빈 문자열 허용X
    @NotBlank(message = "공백 금지")
    @Size(min = 2, max = 100)
    private String title;

    // null 허용 X, 공백 허용 O
    @NotNull
    private String content;

    @NotBlank
    @Size(min = 3, max = 14)
    private String writer;

    // 왜 @Getter가 안될까
    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }
}
