package com.example.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewGalleryImageDTO {
    private String imgname;
    private int gno;
    private int imgsize;
}
