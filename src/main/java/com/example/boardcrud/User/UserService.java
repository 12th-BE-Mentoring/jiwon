package com.example.boardcrud.User;
import com.example.boardcrud.global.error.CustomException;
import com.example.boardcrud.global.error.ErrorCode;
import com.example.boardcrud.global.security.jwt.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;

    //User 생성
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        //비번 해시
        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        //(username, 암호화된 비번 사용) User객체 생성
        User user = new User(
                request.getUsername(),
                encodedPassword
        );

        User saveUser = userRepository.save(user);
        return new UserResponse(saveUser.getId(), saveUser.getUsername());
    }

    // User 조회
    @Transactional
    public UserResponse getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserResponse(user.getId(), user.getUsername());
    }

    public String login(UserLoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_LOGIN);
        }

        return jwtTokenProvider.generateAccessToken(
                user.getUsername()
        );
    }
}
