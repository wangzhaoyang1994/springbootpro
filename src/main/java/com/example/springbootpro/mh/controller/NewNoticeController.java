package com.example.springbootpro.mh.controller;

import com.example.springbootpro.mh.entity.NewNotice;
import com.example.springbootpro.mh.service.NewNoticeService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    @Autowired
    private SolrClient solrClient;
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
    @RequestMapping("/solrNews")
    public List<NewNotice> getSolrNewsList(String searchKey) throws IOException, SolrServerException {
        return  newNoticeService.getSolrMhNews(searchKey);
    }
    @RequestMapping("delSolr")
    public String  delSolr() throws IOException, SolrServerException {
        String query = "*:*";
        solrClient.deleteByQuery(query);
        return "删除成功";
    }
}
