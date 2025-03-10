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
    private final GalleryUploadService galleryUploadService;


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
        boolean result = false;

        // 작성한 게시글을 gallerys에 저장하고, 생성된 글번호를 가져옴
        int gno = -999;

        try {
            if (galleryMapper.insertGallery(gal) > 0) {
                gno = gal.getGgno();
            }
        } catch (Exception ex) {
            throw new IllegalStateException("insertGallery 오류 발생!!!");
        }

        // 첨부된 파일을 업로드 처리하고 가져온 글 번호로 첨부된 파일에 대한 정보를 gallery_images에 저장
        List<NewGalleryImageDTO> gis = null;
        if (!ginames.isEmpty()) {   // 첨부파일이 존재한다면

            // 업로드 처리 후 업로드된 파일들의 정보를 리스트 형태로 받아옴.
            try {
                gis = galleryUploadService.processUpload(ginames, gno);
            } catch (Exception e) {
                throw new IllegalStateException("processUpload 오류 발생!!!");
            }

            // 업로드 된 파일의 정보를 gallery_images 테이블에 저장
            // 즉, 첨부 된 파일정보를 개별 행으로 저장
            try {
                for (NewGalleryImageDTO gi : gis) {
                    galleryMapper.insertGalleryImage(gi);
                }
            } catch (Exception e) {
                throw new IllegalStateException("insertGalleryImage 오류 발생!!!");
            }

            // 첨부된 파일들 중 첫번째 이미지 파일을 썸네일 처리
            try {
                galleryUploadService.makeThumbnail(gal.getSimgname(), gis.get(0).getImgname());
            } catch (Exception e) {
                throw new IllegalStateException("makeThumbnail 오류 발생!!!");
            }

            result = true;
        }
        return result;
    }
}
