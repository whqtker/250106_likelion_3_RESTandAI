package ai.OpenAI.domain.comment.repository;

import ai.OpenAI.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // JPA에서 제공하는 메서드명 규칙을 따르면 자동으로 쿼리가 생성됨
    public List<Comment> findByAuthorId(Long authorId);
}
