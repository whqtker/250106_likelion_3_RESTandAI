package ai.OpenAI.domain.member.controller;

import ai.OpenAI.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;
}
