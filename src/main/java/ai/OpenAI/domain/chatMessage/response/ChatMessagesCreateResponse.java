package ai.OpenAI.domain.chatMessage.response;

import ai.OpenAI.domain.chatMessage.entity.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatMessagesCreateResponse {
    private final ChatMessage chatMessage;
}
