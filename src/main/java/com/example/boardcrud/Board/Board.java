package com.example.boardcrud.Board;
import jakarta.persistence.*;

// 스프링 컨테이너에게 Entity임을 나타낸다
@Entity
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

    public Board(){
    }

    // private 필드를 클래스 외부에서 접근할 수 있게 하는 코드
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }
}
