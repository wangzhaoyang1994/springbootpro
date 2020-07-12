package com.example.springbootpro.mh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class News implements Serializable {
    private  int id;//新闻id
    private String newName;//新闻名称
    private String content;//新闻内容
    private Date createDate;//创建日期
    private Date updateDate;//更新日期
    private  List<NewNotice> list;//新闻分类

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

    public List<NewNotice> getList() {
        return list;
    }

    public void setList(List<NewNotice> list) {
        this.list = list;
    }
}
