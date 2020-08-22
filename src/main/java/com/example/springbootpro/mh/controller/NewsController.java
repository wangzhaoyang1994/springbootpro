package com.example.springbootpro.mh.controller;

import com.example.springbootpro.mh.entity.News;
import com.example.springbootpro.mh.service.NewsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/mh/newList")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @RequestMapping("/list")
    public List<News> getNewsList(int noticeId){
        return  newsService.getNewList(noticeId);
    }
    @RequestMapping("/addNews")
    public int addNews(@RequestParam("newList") String newList) throws ParseException {
        JSONObject jsonObject = JSONObject.fromObject(newList);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = jsonObject.optString("createDate");
        News news = new News();
        news.setNewName(jsonObject.optString("newsName"));
        news.setContent(jsonObject.optString("content"));
        news.setCreateDate(format.parse(createDate));
        news.setUpdateDate(format.parse(jsonObject.optString("updateDate")));
        news.setNoticeId(jsonObject.optInt("noticeId"));
        news.setNoticeName(jsonObject.optString("noticeName"));
        return newsService.addNews(news);
    }

}
