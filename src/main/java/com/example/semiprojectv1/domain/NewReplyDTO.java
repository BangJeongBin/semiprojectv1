package com.example.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewReplyDTO {
    private String comments;
    private String userid;
    private Long ref;
    private Long pno;
}
