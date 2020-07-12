package com.example.springbootpro.mh.service.impl;

import com.example.springbootpro.mh.entity.mhPicture;
import com.example.springbootpro.mh.mapper.MhPictureMapper;
import com.example.springbootpro.mh.service.mhPictureService;
import com.example.springbootpro.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("mhPictureService")
public class mhPictureServiceImpl implements mhPictureService {
    private static int ExpireTime = -1; //redis缓存不过期
    @Autowired
    private MhPictureMapper mhPictureMapper;
    @Resource
    private RedisUtil redisUtil;
    @Override
    public List<mhPicture> getMhPictureList() {
        List<mhPicture> mhPictures = null;
        String mhPic = "mhPic";
        if (redisUtil.hasKey(mhPic)){
            mhPictures=(List<mhPicture>)redisUtil.get(mhPic);
        }else{
            mhPictures=mhPictureMapper.getMhPictureList();
            redisUtil.set(mhPic,mhPictures,ExpireTime);
        }
        return mhPictures;
    }

    @Override
    public List<mhPicture> getNoticePictureList() {
        List<mhPicture> noticePictures = null;
        String noticePic = "noticePic";
        if (redisUtil.hasKey(noticePic)){
            noticePictures=(List<mhPicture>)redisUtil.get(noticePic);
        }else{
            noticePictures=mhPictureMapper.getNoticePictureList();
            redisUtil.set(noticePic,noticePictures,ExpireTime);
        }
        return noticePictures;
    }
}
