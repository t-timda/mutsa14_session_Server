package MutsaLoginServer.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

    @GetMapping
    public String getMyPage(Authentication authentication) {
        // 토큰 인증 성공 시 호출되는 기능
        // authentication.getName()을 통해 로그인한 아이디 확인 가능
        return "환영합니다! " + authentication.getName() + "님의 마이페이지 접근에 성공했습니다.";
    }
}