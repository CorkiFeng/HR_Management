package com.hrmanager.hr_manager.Service.impl;

import com.hrmanager.hr_manager.Dao.mapper.UploadfileMapper;
import com.hrmanager.hr_manager.Model.Uploadfile;
import com.hrmanager.hr_manager.Model.UploadfileExample;
import com.hrmanager.hr_manager.Service.FileService;
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
