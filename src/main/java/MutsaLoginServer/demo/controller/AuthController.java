package MutsaLoginServer.demo.controller;

import MutsaLoginServer.demo.dto.LoginRequest;
import MutsaLoginServer.demo.dto.SignupRequest;
import MutsaLoginServer.demo.dto.TokenResponse;
import MutsaLoginServer.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return "회원가입 완료";
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    // 리프레시 토큰을 전달받아 새로운 토큰 세트를 반환하는 엔드포인트 기능
    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestBody String refreshToken) {
        return authService.reissue(refreshToken);
    }
}