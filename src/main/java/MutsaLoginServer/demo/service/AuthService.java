package MutsaLoginServer.demo.service;

import MutsaLoginServer.demo.config.JwtTokenProvider;
import MutsaLoginServer.demo.entity.User;
import MutsaLoginServer.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // JWT 발급 도구 주입 (별도 구현 필요)
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입 로직
    @Transactional
    public void signup(String username, String password) {
        // 중복 아이디 확인
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디");
        }

        // 유저 생성 및 암호화 저장
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // 비밀번호 암호화
                .build();

        userRepository.save(user);
    }

    // 로그인 로직
    public String login(String username, String password) {
        // 사용자 확인
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        // 비밀번호 검증
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        // 토큰 생성 및 반환
        return jwtTokenProvider.createToken(username);
    }
}