package ai.OpenAI.domain.comment.service;

import ai.OpenAI.domain.comment.entity.Comment;
import ai.OpenAI.domain.comment.repository.CommentRepository;
import ai.OpenAI.domain.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public RsData<List<Comment>> findByAuthorId(Long authorId) {
        List<Comment> comments = commentRepository.findByAuthorId(authorId);

        return RsData.of("200", "댓글 조회 성공", comments);
    }
}
