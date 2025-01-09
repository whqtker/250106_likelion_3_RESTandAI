package ai.OpenAI.domain.tag.repository;

import ai.OpenAI.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByAuthorId(Long authorId);
    List<Tag> findByAuthorUserName(String userName);
}
