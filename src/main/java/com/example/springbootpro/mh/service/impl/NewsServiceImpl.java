package com.example.springbootpro.mh.service.impl;

import com.example.springbootpro.mh.entity.News;
import com.example.springbootpro.mh.mapper.NewsMapper;
import com.example.springbootpro.mh.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NewsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public List<News> getNewList() {
        return newsMapper.getNewList();
    }
}
