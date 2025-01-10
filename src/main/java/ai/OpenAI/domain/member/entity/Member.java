package ai.OpenAI.domain.member.entity;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.global.jpa.BaseEntity;
import ai.OpenAI.domain.tag.entity.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
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
    @Column(unique = true)
    private String userName;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String refreshToken;

//    @OneToMany(mappedBy = "author")
//    private List<Article> articles;
//
//    @OneToMany
//    private List<Tag> tags;
}
