package ai.OpenAI.domain.article.entity;

import ai.OpenAI.domain.global.jpa.BaseEntity;
import ai.OpenAI.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {
    private String title;
    private String content;

    @ManyToOne
    private Member member;
}
