package MutsaLoginServer.demo.controller;

import MutsaLoginServer.demo.dto.LoginRequest;
import MutsaLoginServer.demo.dto.SignupRequest;
import MutsaLoginServer.demo.dto.TokenResponse;
import MutsaLoginServer.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입 요청을 처리하는 기능
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return "회원가입 완료";
    }

    // 로그인 요청을 처리하는 기능
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    // 토큰 재발급 요청을 처리하는 기능
    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestBody String refreshToken) {
        return authService.reissue(refreshToken);
    }

    // 로그아웃 요청을 처리하는 기능
    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        if (authentication != null) {
            // 시큐리티 컨텍스트에 담긴 유저 이메일로 로그아웃을 진행하는 기능
            authService.logout(authentication.getName());
            return "로그아웃 성공";
        }
        return "로그인 상태가 아닙니다.";
    }
}