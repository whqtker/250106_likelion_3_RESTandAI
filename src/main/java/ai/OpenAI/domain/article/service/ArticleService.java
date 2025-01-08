package ai.OpenAI.domain.article.service;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.article.repository.ArticleRepository;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

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
}
