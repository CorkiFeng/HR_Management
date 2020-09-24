package com.service.impl;

import com.mapper.UploadfileMapper;
import com.model.Uploadfile;
import com.model.UploadfileExample;
import com.model.User;
import com.model.UserExample;
import com.service.FileService;
import com.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileServiceImpl  implements FileService {

    @Autowired
    protected UploadfileMapper uploadfileMapper;

    @Override
    public int insert(Uploadfile uploadfile) {
        return uploadfileMapper.insert(uploadfile);
    }

    @Override
    public List<Uploadfile> findAllfiles(UploadfileExample uploadfileExample) {
        return uploadfileMapper.selectByExample(uploadfileExample);
    }

    @Override
    public int deleteByKey(int key) {
        return uploadfileMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int countByExample(UploadfileExample example) {
        return uploadfileMapper.countByExample(example);
    }

    @Override
    public List<Uploadfile> selectByExample(UploadfileExample example) {
        return uploadfileMapper.selectByExample(example);
    }

}
