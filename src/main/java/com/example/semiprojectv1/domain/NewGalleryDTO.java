package com.example.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewGalleryDTO {
    private int ggno;
    private String title;
    private String userid;
    private String simgname;
    private String contents;
}
