package MutsaLoginServer.demo.controller;

import MutsaLoginServer.demo.dto.SignupRequest;
import MutsaLoginServer.demo.dto.LoginRequest;
import MutsaLoginServer.demo.dto.TokenResponse;
import MutsaLoginServer.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        authService.signup(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Signup Success");
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}