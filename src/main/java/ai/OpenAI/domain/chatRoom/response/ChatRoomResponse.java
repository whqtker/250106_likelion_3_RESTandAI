package ai.OpenAI.domain.chatRoom.response;

import ai.OpenAI.domain.chatRoom.dto.ChatRoomDto;
import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatRoomResponse {
    private final ChatRoomDto chatRoom;
}
