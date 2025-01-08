package ai.OpenAI.domain.article.service;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.article.repository.ArticleRepository;
import ai.OpenAI.domain.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public RsData<Article> write(String title, String content) {
        Article article = Article.builder()
                .title(title)
                .content(content)
                .build();

        articleRepository.save(article);
        return RsData.of("200", "글 작성 성공", article);
    }
}
