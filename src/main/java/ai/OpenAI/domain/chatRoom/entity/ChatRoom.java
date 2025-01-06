package ai.OpenAI.domain.chatRoom.entity;

import ai.OpenAI.domain.chatMessage.entity.ChatMessage;
import ai.OpenAI.domain.global.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ChatRoom extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ChatMessage> chatMessages = new ArrayList<>();
}
