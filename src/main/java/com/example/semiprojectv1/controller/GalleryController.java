package com.example.semiprojectv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    @GetMapping("/list")
    public String list() {
        return "views/gallery/list";
    }
}
