package com.example.boardcrud.dto;

import lombok.AllArgsConstructor;

public class BoardResponse {

    private String writer;

    private String title;

    private String content;

    // 조회한 데이터를 담음
    public BoardResponse(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
