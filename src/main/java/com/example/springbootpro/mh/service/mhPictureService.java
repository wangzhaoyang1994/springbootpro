package com.example.springbootpro.mh.service;


import com.example.springbootpro.mh.entity.mhPicture;

import java.util.List;

public interface mhPictureService {
    List<mhPicture> getMhPictureList();
    List<mhPicture> getNoticePictureList();
}
