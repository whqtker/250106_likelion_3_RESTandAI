package ai.OpenAI.domain.chatRoom.dto;

import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomDto {
    private final Long id;
    private final String name;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;

    public ChatRoomDto(ChatRoom chatRoom){
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
        this.createDate = chatRoom.getCreateDate();
        this.modifyDate = chatRoom.getModifyDate();
    }
}
