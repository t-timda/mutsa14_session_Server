package MutsaLoginServer.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyPageController {

    @GetMapping("/mypage")
    public String myPage(Authentication authentication) {
        return authentication.getName() + "님, 환영합니다!";
    }
}