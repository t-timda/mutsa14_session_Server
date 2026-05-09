package MutsaLoginServer.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;

    @Column(nullable = false)
    private Long tvMazeId; // TVMaze API의 원본 ID를 저장하는 기능

    @Column(nullable = false)
    private String name; // 컨텐츠 제목을 저장하는 기능

    @Column
    private String imageUrl; // 이미지 URL을 저장하는 기능

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 컨텐츠를 저장한 사용자와 연관 관계를 맺는 기능

    @Builder
    public Content(Long tvMazeId, String name, String imageUrl, User user) {
        this.tvMazeId = tvMazeId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.user = user;
    }
}