package ai.OpenAI.domain.member.entity;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.global.jpa.BaseEntity;
import ai.OpenAI.domain.tag.entity.Tag;
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

    @OneToMany(mappedBy = "author")
    private List<Article> articles;

    @OneToMany
    private List<Tag> tags;
}
