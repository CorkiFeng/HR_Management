package com.mapper;

import com.model.Uploadfile;
import com.model.UploadfileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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