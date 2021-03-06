package com.example.springbootpro.mh.entity;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    @Field("id")
    private  int id;//新闻id
    @Field("news_newName")
    private String newName;//新闻名称
    @Field("news_content")
    private String content;//新闻内容
    @Field("news_createDate")
    private Date createDate;//创建日期
    @Field("news_updateDate")
    private Date updateDate;//更新日期
    private  int noticeId;//新闻分类id
    private  String noticeName;//新闻分类名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }
}
