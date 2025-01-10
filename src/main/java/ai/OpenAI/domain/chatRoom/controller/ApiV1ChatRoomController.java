package ai.OpenAI.domain.chatRoom.controller;

import ai.OpenAI.domain.chatRoom.dto.ChatRoomDto;
import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import ai.OpenAI.domain.chatRoom.request.ChatRoomCreateRequest;
import ai.OpenAI.domain.chatRoom.response.ChatRoomCreateResponse;
import ai.OpenAI.domain.chatRoom.response.ChatRoomResponse;
import ai.OpenAI.domain.chatRoom.response.ChatRoomsResponse;
import ai.OpenAI.domain.chatRoom.service.ChatRoomService;
import ai.OpenAI.domain.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://cdpn.io")
public class ApiV1ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping("")
    public RsData<ChatRoomsResponse> showRooms(){
        List<ChatRoomDto> chatRooms = this.chatRoomService.findAll();

        return new RsData<>("200", "채팅방 목록 조회 완료", new ChatRoomsResponse(chatRooms));
    }

    @GetMapping("/{roomId}")
    public RsData<ChatRoomResponse> showRoomById(@PathVariable("roomId") Long id){
        ChatRoom chatRoom = this.chatRoomService.findById(id);
        if(chatRoom == null){
            return new RsData<>("500", "%d번 채팅방 조회 불가능: 존재하지 않음".formatted(id), null);
        }

        ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom);
        return new RsData<>("200", "%d번 채팅방 조회 완료".formatted(id) , new ChatRoomResponse(chatRoomDto));
    }

    @PostMapping("")
    public RsData<ChatRoomCreateResponse> createRoom(@Valid @RequestBody ChatRoomCreateRequest chatRoomCreateRequest){
        ChatRoom chatRoom = this.chatRoomService.save(chatRoomCreateRequest.getName());

        return new RsData<>("200", "채팅방 생성 완료", new ChatRoomCreateResponse(chatRoom));
    }
}
