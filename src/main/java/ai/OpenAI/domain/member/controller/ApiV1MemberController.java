package ai.OpenAI.domain.member.controller;

import ai.OpenAI.domain.global.jwt.JwtProvider;
import ai.OpenAI.domain.global.rsData.RsData;
import ai.OpenAI.domain.member.dto.MemberDto;
import ai.OpenAI.domain.member.dto.MemberRequest;
import ai.OpenAI.domain.member.entity.Member;
import ai.OpenAI.domain.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

        // 응답 데이터에 accessToken이라는 이름으로 토큰 발급
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);

        response.addCookie(cookie);

        String refreshToken = member.getRefreshToken();
        Cookie refreshTokenCookie  = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(60 * 60);
        response.addCookie(refreshTokenCookie);

        return new RsData<>("200", "로그인 성공");
    }

    @PostMapping("/logout")
    public RsData<Void> logout(HttpServletResponse response) {
        // 응답 데이터에 accessToken 이름으로 토큰을 발급
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Cookie refreshTokenCookie  = new Cookie("refreshToken", null);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0);
        response.addCookie(refreshTokenCookie);
        return new RsData<>("200", "로그아웃에 성공하였습니다.");
    }

    @GetMapping("/mypage")
    public RsData<MemberDto> mypage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String accessToken = "";

        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("accessToken")) {
                accessToken = cookie.getValue();
            }
        }

        Map<String, Object> claims = jwtProvider.getClaims(accessToken);
        String userName = (String) claims.get("userName");

        Member member = memberService.getMember(userName);
        return new RsData<>("200", "마이페이지 조회 성공", new MemberDto(member));
    }
}
