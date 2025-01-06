package ai.OpenAI.domain.chatRoom.request;

import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatRoomCreateRequest {
    @NotBlank
    private final String name;
}
