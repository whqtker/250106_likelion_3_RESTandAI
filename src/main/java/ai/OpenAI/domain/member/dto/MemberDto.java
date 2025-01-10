package ai.OpenAI.domain.member.dto;

import ai.OpenAI.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MemberDto {
    private final String userName;

    public MemberDto(Member member) {
        this.userName = member.getUserName();
    }
}
