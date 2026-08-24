package com.example.boardcrud.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        User user = new User(request.getUsername(), request.getPassword());
        User saveUser = userRepository.save(user);
        return new UserResponse(saveUser.getId(), saveUser.getUsername());
    }

    @Transactional
    public UserResponse getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return new UserResponse(user.getId(), user.getUsername());
    }
}
