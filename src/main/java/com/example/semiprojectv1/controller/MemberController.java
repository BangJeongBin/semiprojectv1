package com.example.semiprojectv1.controller;

import com.example.semiprojectv1.domain.MemberDTO;
import com.example.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j  // lombok - log객체 자동 생성
@Controller
@RequestMapping("/member") /* 이렇게 안쓴다면 아래에서 적용하면 됨 */
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "views/member/join";
    }

    @PostMapping("/join")
    public String joinok(MemberDTO member) {
        String returnPage = "redirect:/member/error"; // return 코드의 중복을 방지하기 위해 default 값을 false로 지정

        log.info("submit된 회원정보 : {}", member);  // 넘어온 값을 확인하는 코드 - lombok 사용

        if (memberService.newMember(member))
            returnPage =  "redirect:/member/login";

            return returnPage;
    }


    @GetMapping("/login")
    public String login() {
        return "views/member/login";
    }

    @GetMapping("/myinfo")
    public String myinfo() {
        return "views/member/myinfo";
    }
}
