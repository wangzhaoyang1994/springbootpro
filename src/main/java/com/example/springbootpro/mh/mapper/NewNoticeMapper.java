package com.example.springbootpro.mh.mapper;

import com.example.springbootpro.mh.entity.NewNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface NewNoticeMapper {
    int addSure(String name, Date updateDate);
    List<NewNotice> getNoticeList();
}
