package ai.OpenAI.domain.member.controller;

import ai.OpenAI.domain.global.jwt.JwtProvider;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.member.dto.MemberDto;
import ai.OpenAI.domain.member.dto.MemberRequest;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public RsData<MemberDto> signup(@Valid @RequestBody MemberRequest memberRequest) {
        Member member = memberService.join(memberRequest.getUserName(), memberRequest.getPassword());
        return new RsData<>("200", "회원가입 성공", new MemberDto(member));
    }

    @PostMapping("/signout/{userName}")
    public RsData<MemberDto> signout(@RequestParam("userName") String userName) {
        Member deletedMember = memberService.delete(userName);
        return new RsData<>("200", "회원 탈퇴 성공", new MemberDto(deletedMember));
    }

    @PostMapping("/login")
    public RsData<Void> login(@Valid @RequestBody MemberRequest memberRequest, HttpServletResponse response) {
        Member member = memberService.getMember(memberRequest.getUserName());

        // 토큰 생성
        String token = jwtProvider.genAccessToken(member);
        response.addCookie(new Cookie("accessToken", token));

        return new RsData<>("200", "로그인 성공");
    }

    @PostMapping("/logout")
    public void logout() {
        System.out.println("logout");
    }

    @GetMapping("/mypage")
    public void mypage() {
        System.out.println("mypage");
    }
}
