package ai.OpenAI.domain.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleWriteResponse {
    private final ArticleDto article;
}
