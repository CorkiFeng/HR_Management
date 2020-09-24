package com.service;

import com.model.User;
import com.model.UserExample;
import com.service.base.IBaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserService{
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer number);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    List<User> selectAll();

    User selectByPrimaryKey(Integer number);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserRole(User user);

    List<User> selectUserRoleByName(User user);

}
