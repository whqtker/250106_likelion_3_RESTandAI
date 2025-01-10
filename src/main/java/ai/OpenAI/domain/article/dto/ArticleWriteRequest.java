package ai.OpenAI.domain.article.dto;

import ai.OpenAI.domain.article.entity.Article;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleWriteRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
