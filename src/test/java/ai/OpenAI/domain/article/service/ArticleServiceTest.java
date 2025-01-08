package ai.OpenAI.domain.article.service;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.global.ut.Ut;
import ai.OpenAI.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

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

        Ut.thread.sleep(1000);

        articleService.modify(article, "수정된 제목", "수정된 내용");

        Article article_ = articleService.findById(1L).getData();

        assertThat(article_.getTitle()).isEqualTo("수정된 제목");
    }
}
