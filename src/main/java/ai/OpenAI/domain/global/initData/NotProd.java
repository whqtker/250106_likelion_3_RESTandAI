package ai.OpenAI.domain.global.initData;

import ai.OpenAI.domain.chatMessage.service.ChatMessageService;
import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import ai.OpenAI.domain.chatRoom.service.ChatRoomService;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(ChatRoomService chatRoomService, ChatMessageService chatMessageService, MemberService memberService) {
        return args -> {
            ChatRoom chatroom1 = chatRoomService.save("공부");
            ChatRoom chatroom2 = chatRoomService.save("운동");
            ChatRoom chatroom3 = chatRoomService.save("게임");

            IntStream.rangeClosed(1, 10).forEach(index -> {
                chatMessageService.save(chatroom1, "홍길동","공부 메시지" + index);
                chatMessageService.save(chatroom2, "whqtker", "운동 메시지" + index);
                chatMessageService.save(chatroom3, "가나다", "게임 메시지" + index);
            });

            Member member1 = memberService.join("user1", "1234").getData();
            Member member2 = memberService.join("user2", "1234").getData();
            Member member3 = memberService.join("user3", "1234").getData();        };

    }
}
