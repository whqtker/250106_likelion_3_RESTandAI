package ai.OpenAI.domain.article.service;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.comment.entity.Comment;
import ai.OpenAI.domain.comment.service.CommentService;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.global.ut.Ut;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.service.MemberService;
import ai.OpenAI.domain.tag.entity.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CommentService commentService;

    @DisplayName("글 쓰기")
    @Test
    void t1() {
        RsData<Article> writeRs = articleService.write(1L, "제목", "내용");
        Article article = writeRs.getData();

        assertThat(article.getId()).isGreaterThan(0L);
    }

    @DisplayName("1번 글을 가져온다.")
    @Test
    void t2() {
        Article article = articleService.findById(1L).getData();
        assertThat(article.getTitle()).isEqualTo("제목1");
    }

    @DisplayName("1번 글의 작성자의 username 은 user1 이다.")
    @Test
    void t3() {
        Article article = articleService.findById(1L).getData();
        Member author = article.getAuthor();

        assertThat(author.getUserName()).isEqualTo("user1");
    }

    @DisplayName("1번 글의 제목을 수정한다.")
    @Test
    void t4() {
        Article article = articleService.findById(1L).getData();
        String title = article.getTitle();

        //Ut.thread.sleep(1000);

        articleService.modify(article, title + "!", "수정된 내용");

        Article article_ = articleService.findById(1L).getData();

        assertThat(article_.getTitle()).isEqualTo(title + "!");
    }

    @DisplayName("2번 글에 댓글들을 추가한다.")
    @Test // 테스트는 기본적으로 Rollback 되기 때문에, 테스트 결과가 DB에 반영되지 않음.
    @Rollback(false) // 테스트 종료 후 롤백하지 않음, 즉, 결과에 반영함.
    void t5() {
        Member member1 = memberService.findById(1L).getData();
        Article article2 = articleService.findById(2L).getData();

        article2.addComment(member1, "댓글 입니다.");
    }

    @DisplayName("1번 글의 댓글들을 수정한다.")
    @Test
    void t6() {
        Article article = articleService.findById(1L).getData();

        // 댓글들을 순회하며 수정
        article.getComments().forEach(comment -> {
            articleService.modifyComment(comment, comment.getContent() + "!!");
        });
    }

    @DisplayName("1번 글의 댓글 중 마지막 것을 삭제한다.")
    @Test
    void t7() {
        Article article = articleService.findById(1L).getData();

        // t7 단독 수행 시 댓글이 없기 때문에 에러 발생 -> 댓글 임시 추가
        article.addComment(memberService.findById(1L).getData(), "댓글 추가");

        // 자바 17은 getLast() 메서드를 지원하지 않음.
        Comment lastComment = article.getComments().get(article.getComments().size() - 1);
        article.removeComment(lastComment);
    }

    @DisplayName("1번 게시물의 태그(String)를 반환한다.")
    @Test
    void t9() {
        Article article1 = articleService.findById(1L).getData();

        String tagsStr = article1.getTags().stream()
                .map(tag -> "#" + tag.getContent())
                .collect(Collectors.joining(" "));

        assertThat(tagsStr).isEqualTo("#자바 #백엔드");
    }

    @DisplayName("1번 게시물 toString")
    @Test
    void t10() {
        Article article1 = articleService.findById(1L).getData();

        System.out.println(article1);
    }

//    @DisplayName("1번 회원이 작성한 댓글들")
//    @Test
//    void t11() {
//        List<Comment> articleComments = CommentService.findByAuthorId(1L);
//
//        assertThat(articleComments.size()).isGreaterThan(0);
//    }

//    @DisplayName("1번 회원이 작성한 태그들")
//    @Test
//    void t12() {
//        List<ArticleTag> articleTags = articleTagService.findByAuthorId(1L);
//
//        assertThat(articleTags.size()).isGreaterThan(0);
//    }
//
//    @DisplayName("아이디가 user1 인 회원이 작성한 태그들")
//    @Test
//    void t13() {
//        List<ArticleTag> articleTags = articleTagService.findByAuthorUsername("user1");
//
//        assertThat(articleTags.size()).isGreaterThan(0);
//    }
}
