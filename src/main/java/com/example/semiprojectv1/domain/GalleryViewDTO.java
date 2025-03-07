package com.example.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GalleryViewDTO {
    private int gno;
    private String title;
    private String userid;
    private LocalDateTime regdate;
    private String thumbs;
    private String views;
    private String contents;
}
