package MutsaLoginServer.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContentRequest {
    private Long id; // TVMaze의 고유 ID
    private String name; // 컨텐츠 제목
    private ImageUrl image; // 이미지 객체

    @Getter
    @NoArgsConstructor
    public static class ImageUrl {
        private String medium; // 중간 사이즈 이미지 URL
    }
}