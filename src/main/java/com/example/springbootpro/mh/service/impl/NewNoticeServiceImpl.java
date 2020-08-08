package com.example.springbootpro.mh.service.impl;

import com.example.springbootpro.mh.entity.NewNotice;
import com.example.springbootpro.mh.mapper.NewNoticeMapper;
import com.example.springbootpro.mh.mapper.NewsMapper;
import com.example.springbootpro.mh.service.NewNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("NewNoticeService")
public class NewNoticeServiceImpl implements NewNoticeService {
    @Autowired
    private NewNoticeMapper newNoticeMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public int addSure(String name, Date updateDate) {
        return newNoticeMapper.addSure(name, updateDate);
    }

    @Override
    public List<NewNotice> getNoticeList() {
        return newNoticeMapper.getNoticeList();
    }

    @Override
    public List<NewNotice> getMhNews() {
        List<NewNotice> newNoticesList = newNoticeMapper.getMhNews();
        for (NewNotice newNo:newNoticesList) {
            newNo.setChildren(newsMapper.getNewList(newNo.getId()));
        }
        return newNoticesList;
    }
}
