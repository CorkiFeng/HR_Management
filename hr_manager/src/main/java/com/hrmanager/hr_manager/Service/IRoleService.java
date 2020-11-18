package com.hrmanager.hr_manager.Service;


import com.hrmanager.hr_manager.Model.Role;
import com.hrmanager.hr_manager.Model.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleService {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAll();
}
