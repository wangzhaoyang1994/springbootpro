package com.example.springbootpro.mh.service;

import com.example.springbootpro.mh.entity.NewNotice;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface NewNoticeService {
    int addSure(String name, Date updateDate);
    List<NewNotice> getNoticeList();
    List<NewNotice> getMhNews();
    List<NewNotice> getSolrMhNews(String searchKey) throws IOException, SolrServerException;
}
