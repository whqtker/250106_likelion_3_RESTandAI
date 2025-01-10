package ai.OpenAI.domain.article.service;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.article.repository.ArticleRepository;
import ai.OpenAI.domain.comment.entity.Comment;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용 트랜잭션, 따로 트랜잭션이 붙은 메서드는 해당 X
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public Article write(String title, String content) {
        Article article = Article.builder()
                .title(title)
                .content(content)
                .build();

        articleRepository.save(article);
        return article;
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Transactional
    public Article modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

        // JPA의 영속성 컨텍스트가 변경을 감지하여 알아서 update 쿼리를 날려줌: dirty checking
        // 트랜잭션이 안 걸려있다면 update 쿼리가 날라가지 않음.
        // articleRepository.save(article);

        return article;
    }

    @Transactional
    public RsData<Comment> modifyComment(Comment comment, String content) {
        comment.setContent(content);

        // 마찬가지로 dirty checking
        return RsData.of("200", "댓글 수정 성공", comment);
    }

    @Transactional
    public Article delete(Long id) {
        Article article = articleRepository.findById(id).orElse(null);

        if(article != null) {
            articleRepository.delete(article);
        }
        return article;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
