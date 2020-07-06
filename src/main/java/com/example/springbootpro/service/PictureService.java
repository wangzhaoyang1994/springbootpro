package com.example.springbootpro.service;

import net.sf.json.JSONObject;

public interface PictureService {
    JSONObject selectAllPicByPage(int current,int pageSize);
    int insertPic(String picName,String picUrl);
}
