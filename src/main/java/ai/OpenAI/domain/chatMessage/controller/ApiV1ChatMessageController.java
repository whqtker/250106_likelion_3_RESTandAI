package ai.OpenAI.domain.chatMessage.controller;

import ai.OpenAI.domain.chatMessage.dto.ChatMessageDto;
import ai.OpenAI.domain.chatMessage.entity.ChatMessage;
import ai.OpenAI.domain.chatMessage.request.ChatMessagesCreateRequest;
import ai.OpenAI.domain.chatMessage.response.ChatMessagesCreateResponse;
import ai.OpenAI.domain.chatMessage.response.ChatMessagesResponse;
import ai.OpenAI.domain.chatMessage.service.ChatMessageService;
import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import ai.OpenAI.domain.chatRoom.service.ChatRoomService;
import ai.OpenAI.domain.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ApiV1ChatMessageController {
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/{roomId}/messages")
    public RsData<ChatMessagesResponse> getMessagesAfterMessageId(@PathVariable("roomId") Long roomId, @RequestParam("afterChatMessageId") Long afterChatMessageId) {
        ChatRoom chatRoom = chatRoomService.findById(roomId);
        if(chatRoom == null) {
            return RsData.of("500", "%d번 채팅방의 %d 이후 메시지 조회 실패".formatted(roomId, afterChatMessageId), null);
        }

        List<ChatMessageDto> chatMessageDtos = chatMessageService.findMessagesAfterId(chatRoom, afterChatMessageId);

        return RsData.of("200", "%d번 채팅방의 %d 이후 메시지 조회 완료".formatted(roomId, afterChatMessageId), new ChatMessagesResponse(chatMessageDtos));
    }

    @PostMapping("/{roomId}/messages")
    public RsData<ChatMessagesCreateResponse> writeMessage(@PathVariable("roomId") Long roomId, @RequestBody ChatMessagesCreateRequest chatMessagesRequest) {
        ChatRoom chatRoom = chatRoomService.findById(roomId);
        if(chatRoom == null) {
            return RsData.of("500", "%d번 채팅방에 메시지 작성 실패".formatted(roomId), null);
        }

        ChatMessage chatMessage = chatMessageService.save(chatRoom, chatMessagesRequest.getWriterName(), chatMessagesRequest.getMessage());

        return RsData.of("200", "%d번 채팅방에 메시지 작성 완료".formatted(roomId), new ChatMessagesCreateResponse(chatMessage));

    }
}
