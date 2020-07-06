package com.example.springbootpro.controller;

import com.example.springbootpro.service.PictureService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pic")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @RequestMapping("/picList")
    public JSONObject getPicListByPage(int current ,int pageSize){
        return pictureService.selectAllPicByPage(current, pageSize);
    }
    @RequestMapping("/addPic")
    public int addPic(String name,String pictureAddress ){
        return  pictureService.insertPic(name, pictureAddress);
    }
}
