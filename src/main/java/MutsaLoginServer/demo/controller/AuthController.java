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
}