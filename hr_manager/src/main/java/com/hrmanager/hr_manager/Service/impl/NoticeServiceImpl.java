package com.hrmanager.hr_manager.Service.impl;


import com.hrmanager.hr_manager.Dao.mapper.NoticeMapper;
import com.hrmanager.hr_manager.Model.Notice;
import com.hrmanager.hr_manager.Model.NoticeExample;
import com.hrmanager.hr_manager.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int insert(Notice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public List<Notice> findAllNotice(NoticeExample noticeExample) {
        return noticeMapper.selectByExample(noticeExample);
    }

    @Override
    public List<Notice> selectByExample(NoticeExample noticeExample) {
        return noticeMapper.selectByExample(noticeExample);
    }

    @Override
    public Notice findById(int id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByKey(int key) {
        return noticeMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int countByExample(NoticeExample example) {
        return noticeMapper.countByExample(example);
    }
}
