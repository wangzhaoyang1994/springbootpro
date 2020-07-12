package com.example.springbootpro.mh.entity;

import com.example.springbootpro.myinterface.ExcelColumn;

public class mhPicture {
    @ExcelColumn(value = "序号",col = 1)
    private int id;
    @ExcelColumn(value = "图片名称",col = 2)
    private String picName;
    @ExcelColumn(value = "图片路径",col = 3)
    private String picUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
