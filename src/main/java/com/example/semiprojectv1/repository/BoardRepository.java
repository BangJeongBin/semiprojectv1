package com.example.semiprojectv1.repository;

import com.example.semiprojectv1.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardRepository {
                                                                                // limit "시작 인덱스", "증감 값"
    @Select("select bno, title, userid, regdate, views, thumbs from boards order by bno desc limit #{stnum}, #{pageSize}")
    List<BoardDTO> selectBoard(int stnum, int pageSize);

    @Select("select ceil(count(bno) / #{pageSize}) cntpg from boards")
    int countPagesBoard(int pageSize);

    /*List<BoardDTO> selectFindBoard(int stnum, int pageSize, String findtype, String findkey);*/
    List<BoardDTO> selectFindBoard(Map<String, Object> params);
}
