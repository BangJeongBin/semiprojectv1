package com.example.semiprojectv1.controller;

import com.example.semiprojectv1.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


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

        return "views/board/view";
    }


    @GetMapping("/write")
    public String write() {

        return "views/board/write";
    }
}
