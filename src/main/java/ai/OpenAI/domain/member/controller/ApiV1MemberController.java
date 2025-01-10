package ai.OpenAI.domain.member.controller;

import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.member.dto.MemberDto;
import ai.OpenAI.domain.member.dto.MemberRequest;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {
    private final MemberService memberService;

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
    public void login(@Valid @RequestBody MemberRequest memberRequest) {
        Member member = memberService.getMember(memberRequest.getUserName());
        System.out.println("login");
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
