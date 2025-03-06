package com.example.semiprojectv1.service;

import com.example.semiprojectv1.domain.Gallery;
import com.example.semiprojectv1.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryMapper;


    @Override
    public List<Gallery> selectGallery() {
        return galleryMapper.selectGallery();
    }
}
