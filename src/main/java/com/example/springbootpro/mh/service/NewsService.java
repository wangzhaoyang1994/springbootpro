package com.example.springbootpro.mh.service;

import com.example.springbootpro.mh.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getNewList(int noticeId);
    int addNews(News news);
}
