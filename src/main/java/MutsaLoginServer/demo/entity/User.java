package MutsaLoginServer.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users") // 테이블 이름을 예약어인 'user' 대신 'users'로 지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}