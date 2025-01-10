package ai.OpenAI.domain.article.dto;

import ai.OpenAI.domain.article.entity.Article;
import lombok.Data;

@Data
public class ArticleWriteRequest {
    private String title;
    private String content;
}
