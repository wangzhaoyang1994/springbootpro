package com.example.springbootpro.mapper;

import com.example.springbootpro.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PictureMapper {
    List<Picture> selectAllPic();
    int insertPic(String picName,String picUrl);
    List<Picture> selectExport();
    int delPic();
}
