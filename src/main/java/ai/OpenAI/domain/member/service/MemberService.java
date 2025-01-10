package ai.OpenAI.domain.member.service;

import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(String userName, String password) {
        Member member = memberRepository.findByUserName(userName);
        if(member != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        Member newMember = Member.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password)) // 단방향 암호화
                .build();

        return memberRepository.save(newMember);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Member delete(String userName) {
        Member member = memberRepository.findByUserName(userName);
        memberRepository.delete(member);
        return member;
    }

    public Member getMember(String userName) {
        return memberRepository.findByUserName(userName);
    }
}
