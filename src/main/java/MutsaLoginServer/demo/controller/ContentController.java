package MutsaLoginServer.demo.controller;

import MutsaLoginServer.demo.dto.ContentRequest;
import MutsaLoginServer.demo.entity.Content;
import MutsaLoginServer.demo.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    // 프론트엔드에서 보낸 컨텐츠 데이터를 DB에 저장하는 기능
    @PostMapping
    public String saveContent(@RequestBody ContentRequest request, Authentication authentication) {
        contentService.saveContent(request, authentication.getName());
        return "컨텐츠 저장 완료";
    }

    // 로그인된 유저의 컨텐츠 리스트를 반환하는 기능
    @GetMapping
    public List<Content> getMyContents(Authentication authentication) {
        return contentService.getMyContents(authentication.getName());
    }
}