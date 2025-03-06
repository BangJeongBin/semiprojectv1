package com.example.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardReplyDTO {
    private Board bd;
    private List<?> rps;

    public BoardReplyDTO(Board bd, List<?> rps) {
        this.bd = bd;
        this.rps = rps;
    }
}
