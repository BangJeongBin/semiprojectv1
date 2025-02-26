package com.example.semiprojectv1.repository;

import com.example.semiprojectv1.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardRepository {

    @Select("select bno, title, userid, regdate, views, thumbs from boards order by bno desc")
    List<BoardDTO> selectBoard();
}
