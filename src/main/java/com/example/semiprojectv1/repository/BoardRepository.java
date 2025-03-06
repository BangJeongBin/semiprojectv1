package com.example.semiprojectv1.repository;

import com.example.semiprojectv1.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardRepository {
    // limit "시작 인덱스", "증감 값"
    @Select("select bno, title, userid, regdate, views, thumbs from boards order by bno desc limit #{stnum}, #{pageSize}")
    List<BoardDTO> selectBoard(int stnum, int pageSize);

    //@Select("select ceil(count(bno) / #{pageSize}) cntpg from boards")
    int countPagesBoard(int pageSize);

    @Select("select count(bno) cntbd from boards")
    int countBoard();

    /*List<BoardDTO> selectFindBoard(int stnum, int pageSize, String findtype, String findkey);*/
    List<BoardDTO> selectFindBoard(Map<String, Object> params);

    int countFindBoard(Map<String, Object> params);

    @Select("select * from boards where bno = #{bno}")
    Board selectOneBoard(int bno);

    @Update("update boards set views = views + 1 where bno = #{bno}")
    void updateViewOne(int bno);

    @Insert("insert into boards(title, userid, contents) values (#{title}, #{userid}, #{contents})")
    int insertBoard(NewBoardDTO newBoardDTO);

    @Insert("insert into replys(userid, comments, ref, pno) values (#{userid}, #{comments}, last_insert_id() + 1, #{pno})")
    int insertReply(NewReplyDTO newReplyDTO);

    @Select("select * from replys where pno = #{pno} order by ref")
    List<Reply> selectReply(int bno);

    @Insert("insert into replys(userid, comments, ref, pno) values (#{userid}, #{comments}, #{ref}, #{pno})")
    int insertComment(NewReplyDTO newReplyDTO);
}
