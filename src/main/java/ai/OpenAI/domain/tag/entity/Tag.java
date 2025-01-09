package ai.OpenAI.domain.tag.entity;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class Tag extends BaseEntity {
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
}
