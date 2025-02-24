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
        // 회원가입 처리 시 기타오류 발생에 대한 응답코드 설정
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 회원정보 : {}", member);  // 넘어온 값을 확인하는 코드 - lombok 사용

        try {
            // 정상적으로 처리되는 경우 상태코드 200으로 응답
            memberService.newMember(member);
            response =  ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            // 비정상 처리 시 상태코드 400으로 응답 - 클라이언트 쟐못
            // ex) 중복 아이디, 이메일 사용 시 -  unique index
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // 비정상 처리 시 상태코드 500으로 응답 - 서버 쟐못
            e.printStackTrace();
        }

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
