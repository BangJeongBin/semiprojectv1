package com.example.semiprojectv1.controller;

import com.example.semiprojectv1.domain.MemberDTO;
import com.example.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j  // lombok - logging객체 자동 생성
@Controller
@RequestMapping("/member") /* 이렇게 안쓴다면 아래에서 적용하면 됨 */
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "views/member/join";
    }

    // ResponseEntity는 스프링에서 HTTP와 관련된 기능을 구현할 때 사용
    // 상태코드, HTTP헤더, HTTP본문 등을 명시적으로 설정 가능.
    @PostMapping("/join")
    public ResponseEntity<?> joinok(MemberDTO member) {
        ResponseEntity<?> response = ResponseEntity.badRequest().build();

        log.info("submit된 회원정보 : {}", member);  // 넘어온 값을 확인하는 코드 - lombok 사용

        if (memberService.newMember(member))
            // 정상적으로 처리되는 경우
            response =  ResponseEntity.ok().build();

            return response;
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
