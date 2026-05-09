package MutsaLoginServer.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private String accessToken; // 프론트엔드가 받을 토큰
}