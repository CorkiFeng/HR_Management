package com.service;

import com.model.Notice;
import com.model.NoticeExample;

import java.util.List;

public interface NoticeService {

    int insert(Notice notice);

    List<Notice> findAllNotice(NoticeExample noticeExample);

    List<Notice> selectByExample(NoticeExample noticeExample);

    Notice findById(int id);

    int deleteByKey(int key);

    int countByExample(NoticeExample example);
}
