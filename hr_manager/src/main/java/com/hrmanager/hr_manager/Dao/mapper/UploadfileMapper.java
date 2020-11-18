package com.hrmanager.hr_manager.Dao.mapper;


import com.hrmanager.hr_manager.Model.Uploadfile;
import com.hrmanager.hr_manager.Model.UploadfileExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadfileMapper {
    int countByExample(UploadfileExample example);

    int deleteByExample(UploadfileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Uploadfile record);

    int insertSelective(Uploadfile record);

    List<Uploadfile> selectByExample(UploadfileExample example);

    Uploadfile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Uploadfile record, @Param("example") UploadfileExample example);

    int updateByExample(@Param("record") Uploadfile record, @Param("example") UploadfileExample example);

    int updateByPrimaryKeySelective(Uploadfile record);

    int updateByPrimaryKey(Uploadfile record);
}