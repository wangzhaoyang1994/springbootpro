package com.example.springbootpro.service.impl;

import com.example.springbootpro.entity.Picture;
import com.example.springbootpro.mapper.PictureMapper;
import com.example.springbootpro.service.PictureService;
import com.example.springbootpro.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("PictureService")
public class PictureServiceImpl implements PictureService {
    private static int ExpireTime = -1; //redis缓存不过期
    @Autowired
    private PictureMapper pictureMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public JSONObject selectAllPicByPage(int current, int pageSize) {
        JSONObject picJson = new JSONObject();
        PageHelper.startPage(current, pageSize);
        List<Picture> list = pictureMapper.selectAllPic();
        PageInfo<Picture> pageInfo = null;
        if (redisUtil.hasKey(current+"")) { //如果存在key
            pageInfo = (PageInfo<Picture>) redisUtil.get(current+"");//从缓存中获取数据
        } else {
            pageInfo = new PageInfo<Picture>(list);
            redisUtil.set(current+"", pageInfo, ExpireTime);//将数据存入缓存
        }
        picJson.put("picList", pageInfo);
        return picJson;
    }

    @Override
    public int insertPic(String picName, String picUrl,int lastPage ) {
        // 添加之前删除缓存
        String [] removeKeys = new String[lastPage];
        for (int i = 0; i <lastPage ; i++) {
            removeKeys[i]=(i+1)+"";
        }
        redisUtil.del(removeKeys);
        int result = pictureMapper.insertPic(picName, picUrl);
        return result;
    }

    @Override
    public List<Picture> exportExcel(int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<Picture> list = pictureMapper.selectExport();
        PageInfo<Picture> pageInfo =  new PageInfo<Picture>(list);
        return pageInfo.getList();
    }
}
