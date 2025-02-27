package com.example.semiprojectv1.repository;

import com.example.semiprojectv1.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardRepository {
                                                                                // limit "시작 인덱스", "증감 값"
    @Select("select bno, title, userid, regdate, views, thumbs from boards order by bno desc limit #{stnum}, 25")
    List<BoardDTO> selectBoard(int stnum);
}
