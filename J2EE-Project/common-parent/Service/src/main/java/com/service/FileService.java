package com.service;

import com.model.Uploadfile;
import com.model.UploadfileExample;
import com.service.base.IBaseService;

import java.util.List;

public interface FileService  {

    int insert(Uploadfile uploadfile);

    List<Uploadfile> findAllfiles(UploadfileExample uploadfileExample);

    int deleteByKey(int key);

    int countByExample(UploadfileExample example);

    List<Uploadfile> selectByExample(UploadfileExample example);
}
