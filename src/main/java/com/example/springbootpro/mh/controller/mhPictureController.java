package com.example.springbootpro.mh.controller;

import com.example.springbootpro.mh.entity.mhPicture;
import com.example.springbootpro.mh.service.mhPictureService;
import com.example.springbootpro.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/mh/picture")
public class mhPictureController {
    @Autowired
    private mhPictureService service;
    @Resource
    RedisUtil redisUtil;
    @RequestMapping("/getPictureList")
    @ResponseBody
    public List<mhPicture> getPictureList(){
        return service.getMhPictureList();
    }
    @RequestMapping("/getNoticePictureList")
    @ResponseBody
    public List<mhPicture> getNoticePictureList(){
        return service.getNoticePictureList();
    }
}
