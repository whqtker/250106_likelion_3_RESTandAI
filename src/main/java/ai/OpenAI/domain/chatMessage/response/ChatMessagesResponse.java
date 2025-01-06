package ai.OpenAI.domain.chatMessage.response;

import ai.OpenAI.domain.chatMessage.dto.ChatMessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChatMessagesResponse {
    private final List<ChatMessageDto> chatMessages;
}
