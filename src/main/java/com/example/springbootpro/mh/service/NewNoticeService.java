package com.example.springbootpro.mh.service;

import com.example.springbootpro.mh.entity.NewNotice;

import java.util.Date;
import java.util.List;

public interface NewNoticeService {
    int addSure(String name, Date updateDate);
    List<NewNotice> getNoticeList();
}
