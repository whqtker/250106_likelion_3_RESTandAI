package ai.OpenAI.domain.member.controller;

import ai.OpenAI.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public void signup() {
        System.out.println("signup");
    }

    @PostMapping("/signout")
    public void signout() {
        System.out.println("signout");
    }

    @PostMapping("/login")
    public void login() {
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
