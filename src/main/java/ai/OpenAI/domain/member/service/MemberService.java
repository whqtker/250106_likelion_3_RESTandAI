package ai.OpenAI.domain.member.service;

import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public RsData<Member> join(String userName, String password) {
        Member member = Member.builder()
                .userName(userName)
                .password(password)
                .build();

        memberRepository.save(member);
        return RsData.of("200", "%s님 회원가입 성공".formatted(userName), member);
    }
}
