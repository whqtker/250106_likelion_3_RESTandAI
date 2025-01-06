package ai.OpenAI.domain.chatMessage.entity;

import ai.OpenAI.domain.chatRoom.entity.ChatRoom;
import ai.OpenAI.domain.global.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.prefs.BackingStoreException;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ChatMessage extends BaseEntity {
    private String message;
    private String writerName;

    @ManyToOne
    @JoinColumn(name = "chatRoomId")
    @JsonBackReference
    private ChatRoom chatRoom;
}
