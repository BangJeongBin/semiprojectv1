package com.example.semiprojectv1.repository;

import com.example.semiprojectv1.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GalleryRepository {

    @Select("select gno, title, userid, regdate, thumbs, views, simgname from gallerys order by gno desc")
    List<GalleryListDTO> selectGallery();

    @Update("update gallerys set views = views + 1 where gno = #{gno}")
    void updateViewOne(int gno);

    @Select("select gno, title, userid, regdate, views, thumbs, contents from gallerys where gno = #{gno}")
    GalleryViewDTO selectOneGallery(int gno);

    @Select("select imgname, imgsize from gallery_images where gno = #{gno}")
    List<GalleryImage> selectGalleryImages(int gno);

    int insertGallery(NewGalleryDTO gal);

    @Insert("insert into gallery_images (gno, imgname, imgsize) values (#{gno}, #{imgname}, #{imgsize}) ")
    int insertGalleryImage(NewGalleryImageDTO gi);
}
