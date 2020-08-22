package com.example.springbootpro.mh.service.impl;

import com.example.springbootpro.mh.entity.NewNotice;
import com.example.springbootpro.mh.entity.News;
import com.example.springbootpro.mh.mapper.NewNoticeMapper;
import com.example.springbootpro.mh.mapper.NewsMapper;
import com.example.springbootpro.mh.service.NewNoticeService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("NewNoticeService")
public class NewNoticeServiceImpl implements NewNoticeService {
    @Autowired
    private NewNoticeMapper newNoticeMapper;
    @Autowired
    private SolrClient solrClient;
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
    @Override
    public List<NewNotice> getSolrMhNews() throws IOException, SolrServerException {
        List<NewNotice> getSolrMhNews = this.getMhNews();
        //向solr库中添加数据
        for (int i=0;i<getSolrMhNews.size();i++){
            for (int j=0;j<getSolrMhNews.get(i).getChildren().size();j++){
                News news = new News();
                news.setId(getSolrMhNews.get(i).getChildren().get(j).getId());
                news.setNewName(getSolrMhNews.get(i).getChildren().get(j).getNewName());
                news.setContent(getSolrMhNews.get(i).getChildren().get(j).getContent());
                news.setCreateDate(getSolrMhNews.get(i).getChildren().get(j).getCreateDate());
                news.setUpdateDate(getSolrMhNews.get(i).getChildren().get(j).getUpdateDate());
                news.setNoticeId(getSolrMhNews.get(i).getId());
                solrClient.addBean(news);
                solrClient.commit();
            }
        }
        return getSolrMhNews;
    }
}
