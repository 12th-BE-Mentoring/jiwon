package com.example.boardcrud.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 스프링 컨테이너에게 Entity임을 나타낸다
@Entity
@Getter
@NoArgsConstructor
public class Board {

    // PK 지정
    @Id
    // 기본 키 값을 자동증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column은 속성 추가할거 아니면 굳이
    private Integer id;
    private String title;
    private String writer;
    private String content;

    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    //데이터 추가, 삭제가 아닌 수정은 직접 (Setter사용X)
    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
