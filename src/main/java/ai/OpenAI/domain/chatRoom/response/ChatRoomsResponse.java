package ai.OpenAI.domain.chatRoom.response;

import ai.OpenAI.domain.chatRoom.dto.ChatRoomDto;
import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChatRoomsResponse {
    private final List<ChatRoomDto> chatRooms;
}
