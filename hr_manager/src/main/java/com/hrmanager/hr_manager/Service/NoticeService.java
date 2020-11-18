package com.hrmanager.hr_manager.Service;



import com.hrmanager.hr_manager.Model.Notice;
import com.hrmanager.hr_manager.Model.NoticeExample;

import java.util.List;

public interface NoticeService {

    int insert(Notice notice);

    List<Notice> findAllNotice(NoticeExample noticeExample);

    List<Notice> selectByExample(NoticeExample noticeExample);

    Notice findById(int id);

    int deleteByKey(int key);

    int countByExample(NoticeExample example);
}
