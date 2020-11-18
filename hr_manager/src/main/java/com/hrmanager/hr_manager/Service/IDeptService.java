package com.hrmanager.hr_manager.Service;


import com.hrmanager.hr_manager.Model.Dept;
import com.hrmanager.hr_manager.Model.DeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IDeptService {
    int countByExample(DeptExample example);

    int deleteByExample(DeptExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(Dept record);

    int insertSelective(Dept record);

    List<Dept> selectByExample(DeptExample example);

    Dept selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptExample example);

    int updateByExample(@Param("record") Dept record, @Param("example") DeptExample example);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> selectByAll();
}
