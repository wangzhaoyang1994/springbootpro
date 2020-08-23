package com.example.springbootpro.mh.service.impl;

import com.example.springbootpro.mh.entity.NewNotice;
import com.example.springbootpro.mh.entity.News;
import com.example.springbootpro.mh.mapper.NewNoticeMapper;
import com.example.springbootpro.mh.mapper.NewsMapper;
import com.example.springbootpro.mh.service.NewNoticeService;
import com.example.springbootpro.utils.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<NewNotice> getSolrMhNews(String searchKey) throws IOException, SolrServerException {
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
                solrClient.addBean(news);
                solrClient.commit();
            }
        }
        SolrQuery query =new SolrQuery();
        if(StringUtils.isEmpty(searchKey)){
            query.set("q", "*:*");
        }else {
            query.set("q", "news_content:"+searchKey);
        }
        //启动高亮
        query.setHighlight(true);
        query.addHighlightField("news_content");//设置高亮的域名称
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        QueryResponse queryResponse = solrClient.query(query);
        //得到高亮数据
        Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();

        for (int i=0;i<getSolrMhNews.size();i++){
            for (int j=0;j<getSolrMhNews.get(i).getChildren().size();j++){
                if (map!=null) {
                    Map<String, List<String>> map1 = map.get(getSolrMhNews.get(i).getChildren().get(j).getId() + "");
                    if (map1 != null) {
                        List<String> list = map1.get("news_content");
                        if (list != null) {
                            getSolrMhNews.get(i).getChildren().get(j).setContent(list.get(0));
                        }
                    }
                }
            }
        }

        return getSolrMhNews;
    }
}
