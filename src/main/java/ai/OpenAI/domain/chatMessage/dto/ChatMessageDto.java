package ai.OpenAI.domain.chatMessage.dto;

import ai.OpenAI.domain.chatMessage.entity.ChatMessage;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessageDto {
    private final Long id;
    private final String message;
    private final String writerName;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;

    public ChatMessageDto(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.message = chatMessage.getMessage();
        this.writerName = chatMessage.getWriterName();
        this.createDate = chatMessage.getCreateDate();
        this.modifyDate = chatMessage.getModifyDate();
    }
}
