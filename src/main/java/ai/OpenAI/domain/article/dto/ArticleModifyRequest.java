package ai.OpenAI.domain.article.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleModifyRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
