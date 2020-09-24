package com.service.impl;

import com.mapper.DeptMapper;
import com.model.Dept;
import com.model.DeptExample;
import com.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {

    @Autowired
    protected DeptMapper deptMapper;

    @Override
    public int countByExample(DeptExample example) {
        return deptMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DeptExample example) {
        return deptMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer did) {
        return deptMapper.deleteByPrimaryKey(did);
    }

    @Override
    public int insert(Dept record) {
        return deptMapper.insert(record);
    }

    @Override
    public int insertSelective(Dept record) {
        return deptMapper.insertSelective(record);
    }

    @Override
    public List<Dept> selectByExample(DeptExample example) {
        return deptMapper.selectByExample(example);
    }

    @Override
    public Dept selectByPrimaryKey(Integer did) {
        return deptMapper.selectByPrimaryKey(did);
    }

    @Override
    public int updateByExampleSelective(Dept record, DeptExample example) {
        return deptMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Dept record, DeptExample example) {
        return deptMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Dept record) {
        return deptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Dept record) {
        return deptMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Dept> selectByAll() {
        return deptMapper.selectByAll();
    }
}
