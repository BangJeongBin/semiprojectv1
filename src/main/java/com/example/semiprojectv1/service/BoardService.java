package com.example.semiprojectv1.service;

import com.example.semiprojectv1.domain.*;

import java.util.List;
import java.util.Map;

public interface BoardService {

    BoardListDTO readBoard(int cpg);

    BoardReplyDTO readOneBoardReply(int bno);

    /*List<BoardDTO> findBoard(int cpg, String findtype, String findkey);*/
    BoardListDTO findBoard(int cpg, String findtype, String findkey);

    /*int countFindBoard(String findtype, String findkey);*/
    int countfindBoard(Map<String, Object> params);

    /*Board readOneBoard(int bno);*/

    /*void readOneView(int bno);*/

    boolean newBoard(NewBoardDTO newBoardDTO);

    boolean newReply(NewReplyDTO newReplyDTO);

    /*List<Reply> readReply(int pno);*/

    boolean newComment(NewReplyDTO newReplyDTO);
}
