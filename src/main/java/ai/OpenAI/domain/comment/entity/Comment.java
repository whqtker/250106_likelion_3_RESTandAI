package ai.OpenAI.domain.comment.entity;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.article.service.ArticleService;
import ai.OpenAI.domain.global.jpa.BaseEntity;
import ai.OpenAI.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Comment extends BaseEntity {
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
}
