package ai.OpenAI.domain.article.entity;

import ai.OpenAI.domain.comment.entity.Comment;
import ai.OpenAI.domain.global.jpa.BaseEntity;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder // 상속된 필드를 빌더에 포함
@ToString(callSuper = true) // 상속된 필드를 toString에 포함
public class Article extends BaseEntity {
    private String title;
    private String content;

    // profile에서 N+1 문제를 해결했으므로
    @ManyToOne(fetch = FetchType.LAZY) // default: FetchType.EAGER
    @ToString.Exclude
    private Member author;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true) // default: FetchType.LAZY
    @Builder.Default // 빌더에 기본값 포함, 없으면 comments가 null로 초기화됨.
    @ToString.Exclude // 무한 순환 참조를 방지하기 위해
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude // 무한 순환 참조를 방지하기 위해
    private List<Tag> tags = new ArrayList<>();

    public void addComment(Member member, String content) {
        Comment comment = Comment.builder()
                .author(member)
                .article(this)
                .content(content)
                .build();

        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    // 가변인자를 사용하여 여러 태그를 한번에 추가할 수 있도록 함
    public void addTag(String... contents) {
        for (String content : contents) {
            Tag tag = Tag.builder()
                    .article(this)
                    .author(this.author)
                    .content(content)
                    .build();

            tags.add(tag);
        }
    }
}
