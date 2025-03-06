package com.example.semiprojectv1.repository;

import com.example.semiprojectv1.domain.Gallery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GalleryRepository {

    @Select("select gno, title, userid, thumbs, views, simgname, regdate from gallerys order by gno desc")
    List<Gallery> selectGallery();
}
