package com.example.boardcrud.User;

import com.example.boardcrud.Board.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @OneToMany(mappedBy ="user")
    private List<Board> boards = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
