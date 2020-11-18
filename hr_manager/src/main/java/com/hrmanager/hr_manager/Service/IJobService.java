package com.hrmanager.hr_manager.Service;


import com.hrmanager.hr_manager.Model.Job;
import com.hrmanager.hr_manager.Model.JobExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IJobService {
    int countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Integer jid);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> selectByExample(JobExample example);

    Job selectByPrimaryKey(Integer jid);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    List<Job> selectByAll();
}
