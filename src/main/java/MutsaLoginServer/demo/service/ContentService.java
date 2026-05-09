package MutsaLoginServer.demo.service;

import MutsaLoginServer.demo.dto.ContentRequest;
import MutsaLoginServer.demo.entity.Content;
import MutsaLoginServer.demo.entity.User;
import MutsaLoginServer.demo.repository.ContentRepository;
import MutsaLoginServer.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    // 전달받은 컨텐츠 정보를 현재 유저의 목록으로 저장하는 기능
    @Transactional
    public void saveContent(ContentRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Content content = Content.builder()
                .tvMazeId(request.getId())
                .name(request.getName())
                .imageUrl(request.getImage() != null ? request.getImage().getMedium() : null)
                .user(user)
                .build();

        contentRepository.save(content);
    }

    // 현재 접속한 유저가 저장한 모든 컨텐츠를 조회하는 기능
    @Transactional(readOnly = true)
    public List<Content> getMyContents(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return contentRepository.findAllByUser(user);
    }
}