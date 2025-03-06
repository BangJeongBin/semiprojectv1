package com.example.semiprojectv1.controller;

import com.example.semiprojectv1.domain.NewBoardDTO;
import com.example.semiprojectv1.domain.NewReplyDTO;
import com.example.semiprojectv1.service.BoardService;

import com.example.semiprojectv1.service.GoogleRecaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final GoogleRecaptchaService googleRecaptchaService;


    @GetMapping("/list")
        // RequestParam에 defaultValue를 이용하면 cpg매개변수가 전달되지 않을 경우 기본값인 1이 전달됨
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg,
                       HttpServletResponse response) {

        // 클라이언트 캐시 제어(글 선택 후 뒤로가기 키를 눌러도 상승한 조회수가 적용됨)
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        log.info("board/list 호출!!");

        m.addAttribute("bds", boardService.readBoard(cpg))
         .addAttribute("cpg", cpg)
         .addAttribute("stblk", ((cpg - 1) / 10) * 10 + 1)
         .addAttribute("cntpg", boardService.countBoard());

        return "views/board/list";
    }


    @GetMapping("/find")
    public String find(Model m, @RequestParam(defaultValue = "1") int cpg, String findtype, String findkey) {

        m.addAttribute("bds", boardService.findBoard(cpg, findtype, findkey))
         .addAttribute("cpg", cpg)
         .addAttribute("stblk", ((cpg - 1) / 10) * 10 + 1)
         .addAttribute("cntpg", boardService.countFindBoard(findtype, findkey));

        return "views/board/list";
    }


    @GetMapping("/view")
    public String view(Model m, int bno) {

        boardService.readOneView(bno);
        m.addAttribute("bd", boardService.readOneBoard(bno));
        m.addAttribute("rps", boardService.readReply(bno));

        return "views/board/view";
    }


    @GetMapping("/write")
    public String write(Model m, HttpSession session) {
        String returnPage = "redirect:/member/login";

        // 이 코드를 설정 시 url에서 write 입력시 로그인 창으로 강제 이동
        if (session.getAttribute("loginUser") != null) {
            // 시스템 환경변수에 저장된 사이트키를 불러옴
            m.addAttribute("sitekey", System.getenv("recaptcha.sitekey"));
            returnPage = "views/board/write";
        }
        return returnPage;
    }


    @PostMapping("/write")
    public ResponseEntity<?> writeok(NewBoardDTO newBoardDTO,
                                     @RequestParam("g-recaptcha-response") String gRecaptchaResponse) {

        ResponseEntity<?> response = ResponseEntity.internalServerError().build();
        log.info("submit 된 게시글 정보 : {}", newBoardDTO);
        log.info("submit 된 recaptcha 응답코드 : {}", gRecaptchaResponse);

        try {
            if (!googleRecaptchaService.verifyRecaptcha(gRecaptchaResponse)) {
                throw new IllegalStateException("자동가입방지 코드 오류");
            }

            if (boardService.newBoard(newBoardDTO)) {
                response = ResponseEntity.ok().build();
            }
        } catch (IllegalStateException ex) {
            response = ResponseEntity.badRequest().body(ex.getMessage());
        }
        return response;
    }

    @PostMapping("/reply")
    public String replyok(NewReplyDTO newReplyDTO) {
        String returnPage = "redirect:/board/view?bno=" + newReplyDTO.getPno();

        if (!boardService.newReply(newReplyDTO)) {
            returnPage = "redirect:/board/error?type=1";
        }
        return returnPage;
    }


    @PostMapping("/cmmt")
    public String cmmtok(NewReplyDTO newReplyDTO) {
        String returnPage = "redirect:/board/view?bno=" + newReplyDTO.getPno();

        if (!boardService.newComment(newReplyDTO)) {
            returnPage = "redirect:/board/error?type=1";
        }
        return returnPage;
    }
}
