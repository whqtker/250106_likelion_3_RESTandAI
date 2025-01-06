package ai.OpenAI.domain.chatMessage.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatMessagesCreateRequest {
    @NotBlank
    private final String message;

    @NotBlank
    private final String writerName;
}
