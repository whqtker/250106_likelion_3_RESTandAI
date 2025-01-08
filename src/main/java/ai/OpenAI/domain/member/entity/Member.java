package ai.OpenAI.domain.member.entity;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    private String userName;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Article> articles;
}
