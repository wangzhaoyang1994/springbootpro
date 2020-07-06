package com.example.springbootpro.service.impl;

import com.example.springbootpro.entity.Picture;
import com.example.springbootpro.mapper.PictureMapper;
import com.example.springbootpro.service.PictureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PictureService")
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public JSONObject selectAllPicByPage(int current, int pageSize) {
        JSONObject picJson = new JSONObject();
        PageHelper.startPage(current, pageSize);
        List<Picture> list = pictureMapper.selectAllPic();
        PageInfo<Picture> pageInfo = new PageInfo<Picture>(list);
        picJson.put("picList",pageInfo);
        return picJson;
    }

    @Override
    public int insertPic(String picName, String picUrl) {
        return pictureMapper.insertPic(picName, picUrl);
    }
}
