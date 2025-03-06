package com.example.semiprojectv1.controller;

import com.example.semiprojectv1.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
