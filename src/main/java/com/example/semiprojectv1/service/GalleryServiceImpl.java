package com.example.semiprojectv1.service;

import com.example.semiprojectv1.domain.*;
import com.example.semiprojectv1.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryMapper;


    @Override
    public List<GalleryListDTO> selectGallery() {
        return galleryMapper.selectGallery();
    }


    @Transactional
    @Override
    public GalleryImageDTO readOneGalleryImage(int gno) {

        galleryMapper.updateViewOne(gno);   // 조회수 증가
        GalleryViewDTO gal = galleryMapper.selectOneGallery(gno);    // 본문글 가져오기
        List<GalleryImage> gi = galleryMapper.selectGalleryImages(gno); // 본문글에 포함된 이미지들 가져오기

        return new GalleryImageDTO(gal, gi);
    }

    @Transactional
    @Override
    public boolean newGalleryImage(NewGalleryDTO gal, List<MultipartFile> ginames) {
        // 작성한 게시글을 gallerys에 저장하고, 생성된 글번호를 가져옴
        int gno = -999;
        if (galleryMapper.insertGallery(gal) > 0) {
            gno = gal.getGgno();
        }

        // 첨부된 파일을 업로드 처리하고 가져온 글 번호로 첨부된 파일에 대한 정보를 gallery_images에 저장

        // 첨부된 파일들 중 첫번째 이미지 파일을 썸네일 처리

        return false;
    }
}
