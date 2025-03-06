package com.example.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardListDTO {
    private int cpg;    // 현채 페이지
    private int stblk;  // 페이지당 시작하는 게시물 번호
    private int cntpg;  // 전체 페이지 수
    private int edblk; // 페이지당 끝나는 게시물 번호
    private List<?> bdlist;

    // 페이지네이션 수식 계산을 생성자에서 수행
    public BoardListDTO(int cpg, int totalItems, int pageSize, List<?> bdlist) {
        this.cpg = cpg;
        this.cntpg = (int)Math.ceil((double)totalItems / pageSize);    // 총 페이지수를 구하는 코드
        this.bdlist = bdlist;

        // 페이지네이션 범위 게산
        this.stblk = ((cpg - 1) / 10) * 10 + 1; // 한 페이지에 보여줄 페이지블럭의 첫 번째 번호를 가져옴
        this.edblk = Math.min(stblk + 10 - 1, cntpg); // 둘 중에 작은 값을 가져옴 - 한 페이지에 보여줄 페이지블럭의 마지막 번호를 가져옴
    }
}
