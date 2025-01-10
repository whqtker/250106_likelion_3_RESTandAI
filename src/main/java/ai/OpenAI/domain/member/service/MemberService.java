package ai.OpenAI.domain.member.service;

import ai.OpenAI.domain.global.jwt.JwtProvider;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.global.security.SecurityUser;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Member join(String userName, String password) {
        Member member = memberRepository.findByUserName(userName);
        if(member != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        Member newMember = Member.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password)) // 단방향 암호화
                .build();

        // 새로운 회원을 먼저 저장
        newMember = memberRepository.save(newMember);

        // 저장된 회원 객체를 사용하여 refreshToken 생성 및 설정
        String refreshToken = jwtProvider.genRefreshToken(newMember);
        newMember.setRefreshToken(refreshToken);

        // refreshToken이 설정된 회원 객체를 다시 저장
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

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        return jwtProvider.verify(token);
    }

    // 토큰갱신
    public RsData<String> refreshAccessToken(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));
        String accessToken = jwtProvider.genAccessToken(member);
        return new RsData<>("200", "토큰 갱신에 성공하였습니다.", accessToken);
    }
    // 토큰으로 User 정보 가져오기
    public SecurityUser getUserFromAccessToken(String accessToken) {
        Map<String, Object> payloadBody = jwtProvider.getClaims(accessToken);
        long id = (int) payloadBody.get("id");
        String username = (String) payloadBody.get("username");
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new SecurityUser(id, username, "", authorities);
    }
}
