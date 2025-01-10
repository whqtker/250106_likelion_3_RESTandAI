package ai.OpenAI.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class MemberRequest {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
