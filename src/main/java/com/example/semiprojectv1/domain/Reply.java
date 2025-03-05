package com.example.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Reply {
    private String rno;
    private String comments;
    private String userid;
    private LocalDateTime regdate;
    private String ref;
    private String pno;
}
