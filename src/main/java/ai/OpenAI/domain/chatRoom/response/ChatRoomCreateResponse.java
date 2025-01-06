package ai.OpenAI.domain.chatRoom.response;

import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatRoomCreateResponse {
    private final ChatRoom chatRoom;
}
