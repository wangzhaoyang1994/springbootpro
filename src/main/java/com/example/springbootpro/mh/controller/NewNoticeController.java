package com.example.springbootpro.mh.controller;

import com.example.springbootpro.mh.entity.NewNotice;
import com.example.springbootpro.mh.service.NewNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mh/newNotice")
public class NewNoticeController {
    @Autowired
    private NewNoticeService newNoticeService;
    @RequestMapping("/addSure")
    public int addSure(String name,String updateDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date formatUpdate = format.parse(updateDate);
        return newNoticeService.addSure(name,formatUpdate);
    }
    @RequestMapping("/list")
    public List<NewNotice> getNotice(){
        return newNoticeService.getNoticeList();
    }
    @RequestMapping("/getMhNew")
    @ResponseBody
    public List<NewNotice> getMhNews(){
        return newNoticeService.getMhNews();
    }
}
