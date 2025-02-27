package com.example.semiprojectv1.service;

import com.example.semiprojectv1.domain.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> readBoard(int cpg);

    int countBoard();
}
