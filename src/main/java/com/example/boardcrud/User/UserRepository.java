package com.example.boardcrud.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // 로그인 할 때
    Optional<User> findByUsername(String username);

    // 회원가입 할 때 이미 존재하는지 확인 username
    boolean existByUsername(String username);
}
