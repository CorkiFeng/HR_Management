package com.service.impl;

import com.mapper.RoleMapper;
import com.model.Role;
import com.model.RoleExample;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    protected RoleMapper roleMapper;

    @Override
    public int countByExample(RoleExample example) {
        return roleMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RoleExample example) {
        return roleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public List<Role> selectByExample(RoleExample example) {
        return roleMapper.selectByExample(example);
    }

    @Override
    public Role selectByPrimaryKey(Integer rid) {
        return roleMapper.selectByPrimaryKey(rid);
    }

    @Override
    public int updateByExampleSelective(Role record, RoleExample example) {
        return roleMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Role record, RoleExample example) {
        return roleMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }
}
