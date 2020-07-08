package com.example.springbootpro.service;

import com.example.springbootpro.entity.Picture;
import net.sf.json.JSONObject;

import java.util.List;

public interface PictureService {
    JSONObject selectAllPicByPage(int current,int pageSize);
    int insertPic(String picName,String picUrl,int lastPage );
    List<Picture> exportExcel(int current, int pageSize);
}
