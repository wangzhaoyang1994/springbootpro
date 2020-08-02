package com.example.springbootpro.mh.controller;

import com.example.springbootpro.mh.entity.News;
import com.example.springbootpro.mh.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mh/newList")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @RequestMapping("/list")
    public List<News> getNewsList(){
        return  newsService.getNewList();
    }
}
