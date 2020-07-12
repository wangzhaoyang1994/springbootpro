package com.example.springbootpro.mh.mapper;

import com.example.springbootpro.mh.entity.mhPicture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MhPictureMapper {
    List<mhPicture> getMhPictureList();
    List<mhPicture> getNoticePictureList();
}
