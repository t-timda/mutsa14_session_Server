package MutsaLoginServer.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // 리프레시 토큰을 저장하기 위한 컬럼
    @Column
    private String refreshToken;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 리프레시 토큰을 새로운 토큰으로 갱신하는 기능
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}