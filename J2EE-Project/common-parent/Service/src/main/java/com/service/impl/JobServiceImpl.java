package com.service.impl;

import com.mapper.JobMapper;
import com.model.Job;
import com.model.JobExample;
import com.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements IJobService {

    @Autowired
    protected JobMapper jobMapper;

    @Override
    public int countByExample(JobExample example) {
        return jobMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(JobExample example) {
        return jobMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer jid) {
        return jobMapper.deleteByPrimaryKey(jid);
    }

    @Override
    public int insert(Job record) {
        return jobMapper.insert(record);
    }

    @Override
    public int insertSelective(Job record) {
        return jobMapper.insertSelective(record);
    }

    @Override
    public List<Job> selectByExample(JobExample example) {
        return jobMapper.selectByExample(example);
    }

    @Override
    public Job selectByPrimaryKey(Integer jid) {
        return jobMapper.selectByPrimaryKey(jid);
    }

    @Override
    public int updateByExampleSelective(Job record, JobExample example) {
        return jobMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Job record, JobExample example) {
        return jobMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Job record) {
        return jobMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Job record) {
        return jobMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Job> selectByAll() {
        return jobMapper.selectByAll();
    }
}
