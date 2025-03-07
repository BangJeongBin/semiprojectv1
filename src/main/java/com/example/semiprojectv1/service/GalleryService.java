package com.example.semiprojectv1.service;

import com.example.semiprojectv1.domain.GalleryImageDTO;
import com.example.semiprojectv1.domain.GalleryListDTO;
import com.example.semiprojectv1.domain.GalleryViewDTO;

import java.util.List;

public interface GalleryService {

    List<GalleryListDTO> selectGallery();

    GalleryImageDTO readOneGalleryImage(int gno);
}
