package ai.OpenAI.domain.article.service;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.article.repository.ArticleRepository;
import ai.OpenAI.domain.comment.entity.Comment;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용 트랜잭션, 따로 트랜잭션이 붙은 메서드는 해당 X
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public RsData<Article> write(Long id, String title, String content) {
        Article article = Article.builder()
                .author(Member.builder().id(id).build())
                .title(title)
                .content(content)
                .build();

        articleRepository.save(article);
        return RsData.of("200", "글 작성 성공", article);
    }

    public RsData<Article> findById(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        return RsData.of("200", "글 조회 성공", article);
    }

    @Transactional
    public RsData<Article> modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

        // JPA의 영속성 컨텍스트가 변경을 감지하여 알아서 update 쿼리를 날려줌: dirty checking
        // 트랜잭션이 안 걸려있다면 update 쿼리가 날라가지 않음.
        // articleRepository.save(article);

        return RsData.of("200", "글 수정 성공", article);
    }

    @Transactional
    public RsData<Comment> modifyComment(Comment comment, String content) {
        comment.setContent(content);

        // 마찬가지로 dirty checking
        return RsData.of("200", "댓글 수정 성공", comment);
    }

    @Transactional
    public RsData<Article> delete(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if(article == null){
            return RsData.of("500", "글 삭제 불가능: 존재하지 않음", null);
        }

        articleRepository.delete(article);
        return RsData.of("200", "글 삭제 성공", article);
    }

    public RsData<List<Article>> findAll(){
        return RsData.of("200", "글 전체 조회 성공", articleRepository.findAll());
    }
}
