package ai.OpenAI.domain.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private final ArticleDto article;
}
