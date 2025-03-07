package com.example.semiprojectv1.controller;

import com.example.semiprojectv1.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;


    @GetMapping("/list")
    public String list(Model m) {
        m.addAttribute("gals", galleryService.selectGallery());

        return "views/gallery/list";
    }


    /*Query String (질의문자열)
    URL의 ? 뒤에 key=value 형태로 데이터를 전달하는 방식
    ex) /users?name=John&age=30에서 name과 age가 Query String 매개변수
    스프링 부트에서는 @RequestParam 어노테이션을 사용하여 처리
    검색 조건, 필터링, 정렬 등 복잡한 데이터를 전달하기에 적합

    Path Variable (경로 변수)
    URL 경로 자체에 데이터를 포함시키는 방식
    ex) /users/John/30에서 John, 30이 Path Variable로 사용
    스프링 부트에서는 @PathVariable 어노테이션을 사용하여 처리
    RESTful API 설계에서 자원의 식별자로 사용하기에 적합*/

    // /gallery/view/{글번호}
    @GetMapping("/view/{gno}")
    public String view(Model m, @PathVariable int gno) {
        m.addAttribute("galgi", galleryService.readOneGalleryImage(gno));

        return "views/gallery/view";
    }


    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("sitekey", System.getenv("recaptcha.sitekey"));

        return "views/gallery/write";
    }
}
