package com.example.semiprojectv1.repository;

import com.example.semiprojectv1.domain.GalleryImage;
import com.example.semiprojectv1.domain.GalleryListDTO;
import com.example.semiprojectv1.domain.GalleryViewDTO;
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
}
