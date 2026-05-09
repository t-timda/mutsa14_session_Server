package MutsaLoginServer.demo.repository;

import MutsaLoginServer.demo.entity.Content;
import MutsaLoginServer.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    // 특정 유저가 저장한 모든 컨텐츠 리스트를 찾는 기능
    List<Content> findAllByUser(User user);
}