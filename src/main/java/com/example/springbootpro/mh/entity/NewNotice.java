package com.example.springbootpro.mh.entity;

import java.io.Serializable;
import java.util.Date;

public class NewNotice implements Serializable {
    private  int id;//新闻分类id
    private String noticeNewName;//新闻分类名称
    private Date createDate;//添加日期
    private Date updateDate;//更新日期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoticeNewName() {
        return noticeNewName;
    }

    public void setNoticeNewName(String noticeNewName) {
        this.noticeNewName = noticeNewName;
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
}
