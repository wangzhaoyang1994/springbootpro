package com.example.springbootpro.mh.mapper;

import com.example.springbootpro.mh.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    List<News> getNewList(int noticeId);
    int addNews(News news);
}
