package ai.OpenAI.domain.chatMessage.service;

import ai.OpenAI.domain.chatMessage.dto.ChatMessageDto;
import ai.OpenAI.domain.chatMessage.entity.ChatMessage;
import ai.OpenAI.domain.chatMessage.repository.ChatMessageRepository;
import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import ai.OpenAI.domain.chatRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    public List<ChatMessageDto> findMessagesAfterId(ChatRoom chatRoom, Long messageId) {
        List<ChatMessage> chatMessages = chatRoom.getChatMessages();

        List<ChatMessageDto> chatMessageDtos = chatMessages.stream()
                .filter(chatMessage -> chatMessage.getId() > messageId)
                .map(ChatMessageDto::new)
                .collect(Collectors.toList());

        return chatMessageDtos;
    }

    public ChatMessage save(ChatRoom chatRoom, String writerName, String message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .writerName(writerName)
                .message(message)
                .build();

        this.chatMessageRepository.save(chatMessage);
        chatRoom.getChatMessages().add(chatMessage);



        return chatMessage;
    }
}
