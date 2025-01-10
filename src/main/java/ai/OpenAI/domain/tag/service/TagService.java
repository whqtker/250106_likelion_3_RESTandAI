package ai.OpenAI.domain.tag.service;

import ai.OpenAI.domain.comment.entity.Comment;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.tag.entity.Tag;
import ai.OpenAI.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public RsData<List<Tag>> findByAuthorId(Long authorId) {
        List<Tag> tags = tagRepository.findByAuthorId(authorId);

        return new RsData<>("200", "댓글 조회 성공", tags);
    }

    public RsData<List<Tag>> findByAuthorUserName(String userName) {
        List<Tag> tags = tagRepository.findByAuthorUserName(userName);

        return new RsData<>("200", "댓글 조회 성공", tags);
    }
}
