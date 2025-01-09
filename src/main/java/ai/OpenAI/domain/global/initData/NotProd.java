package ai.OpenAI.domain.global.initData;

import ai.OpenAI.domain.article.entity.Article;
import ai.OpenAI.domain.article.repository.ArticleRepository;
import ai.OpenAI.domain.article.service.ArticleService;
import ai.OpenAI.domain.chatMessage.service.ChatMessageService;
import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import ai.OpenAI.domain.chatRoom.service.ChatRoomService;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.service.MemberService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(ChatRoomService chatRoomService, ChatMessageService chatMessageService,
                                         MemberService memberService, ArticleService articleService
                                         ) {
//        return args -> {
//            ChatRoom chatroom1 = chatRoomService.save("공부");
//            ChatRoom chatroom2 = chatRoomService.save("운동");
//            ChatRoom chatroom3 = chatRoomService.save("게임");
//
//            IntStream.rangeClosed(1, 10).forEach(index -> {
//                chatMessageService.save(chatroom1, "홍길동","공부 메시지" + index);
//                chatMessageService.save(chatroom2, "whqtker", "운동 메시지" + index);
//                chatMessageService.save(chatroom3, "가나다", "게임 메시지" + index);
//            });
//
//            Member member1 = memberService.join("user1", "1234").getData();
//            Member member2 = memberService.join("user2", "1234").getData();
//            Member member3 = memberService.join("user3", "1234").getData();
//
//            Article article1 = articleService.write(member1.getId(), "제목1", "내용1").getData();
//            Article article2 = articleService.write(member1.getId(), "제목2", "내용2").getData();
//
//            Article article3 = articleService.write(member2.getId(), "제목3", "내용3").getData();
//            Article article4 = articleService.write(member2.getId(), "제목4", "내용4").getData();
//
//            article1.addComment(member1, "댓글1");
//            article1.addComment(member1, "댓글2");
//
//            article2.addComment(member1, "댓글3");
//            article2.addComment(member1, "댓글4");
//            article2.addComment(member1, "댓글5");
//
//            article3.addComment(member1, "댓글5");
//            article3.addComment(member1, "댓글6");
//            article3.addComment(member1, "댓글7");
//            article3.addComment(member1, "댓글8");
//            article3.addComment(member1, "댓글9");
//            article3.addComment(member1, "댓글10");
//            article3.addComment(member1, "댓글11");
//            article3.addComment(member1, "댓글12");
//
//            // 트랜잭션이 걸려있지 않기 때문에 addComment는 dirty checking이 일어나지 않음
//            // 따라서 DB에 반영하기 위해 articleRepository.save(article)를 해주어야 함.
//            // 아니면 구조를 바꿔야 하는데, 아래 코드 참고.
//        };


        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) throws Exception {
                ChatRoom chatRoom1 = chatRoomService.save("room1");
                ChatRoom chatRoom2 = chatRoomService.save("room2");
                ChatRoom chatRoom3 = chatRoomService.save("room3");

                IntStream.rangeClosed(1, 10).forEach(num -> {
                    chatMessageService.save(chatRoom1, "홍길동", "채팅메세지" + num);
                });
                Member member1 = memberService.join("user1", "1234").getData();
                Member member2 = memberService.join("user2", "1234").getData();
                Member member3 = memberService.join("user3", "1234").getData();

                Article article1 = articleService.write(member1.getId(), "제목1", "내용1").getData();
                Article article2 = articleService.write(member1.getId(), "제목2", "내용2").getData();

                Article article3 = articleService.write(member2.getId(), "제목3", "내용3").getData();
                Article article4 = articleService.write(member2.getId(), "제목4", "내용4").getData();

                article1.addComment(member1, "댓글1");
                article1.addComment(member1, "댓글2");

                article2.addComment(member1, "댓글3");
                article2.addComment(member1, "댓글4");
                article2.addComment(member1, "댓글5");

                article3.addComment(member1, "댓글5");
                article3.addComment(member1, "댓글6");
                article3.addComment(member1, "댓글7");
                article3.addComment(member1, "댓글8");
                article3.addComment(member1, "댓글9");
                article3.addComment(member1, "댓글10");
                article3.addComment(member1, "댓글11");
                article3.addComment(member1, "댓글12");

                article1.addTag("자바");
                article1.addTag("백엔드");
                article2.addTag("프레임워크", "스프링부트");
                article4.addTag("자바", "스프링부트");
            }
        };
    }
}
