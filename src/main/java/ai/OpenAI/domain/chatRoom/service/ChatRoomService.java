package ai.OpenAI.domain.chatRoom.service;

import ai.OpenAI.domain.chatRoom.dto.ChatRoomDto;
import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import ai.OpenAI.domain.chatRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomDto> findAll(){
        List<ChatRoom> chatRooms = this.chatRoomRepository.findAll();
        List<ChatRoomDto> chatRoomDtos = chatRooms.stream()
                .map(chatRoom -> new ChatRoomDto(chatRoom))
                .collect(Collectors.toList());

        return chatRoomDtos;
    }

    public ChatRoom findById(Long id){
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);
        return chatRoom.orElse(null);
    }

    public ChatRoom save(String name){
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();
        this.chatRoomRepository.save(chatRoom);

        return chatRoom;
    }
}
