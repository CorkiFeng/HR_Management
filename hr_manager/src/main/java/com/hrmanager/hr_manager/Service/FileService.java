package com.hrmanager.hr_manager.Service;



import com.hrmanager.hr_manager.Model.Uploadfile;
import com.hrmanager.hr_manager.Model.UploadfileExample;

import java.util.List;

public interface FileService  {

    int insert(Uploadfile uploadfile);

    List<Uploadfile> findAllfiles(UploadfileExample uploadfileExample);

    int deleteByKey(int key);

    int countByExample(UploadfileExample example);

    List<Uploadfile> selectByExample(UploadfileExample example);
}
