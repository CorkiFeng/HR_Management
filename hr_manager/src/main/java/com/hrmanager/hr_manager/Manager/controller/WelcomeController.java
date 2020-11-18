package com.hrmanager.hr_manager.Manager.controller;

import com.hrmanager.hr_manager.Model.*;
import com.hrmanager.hr_manager.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class WelcomeController {

    @Autowired
    IUserService userService;

    @Autowired
    IDeptService deptService;

    @Autowired
    IJobService jobService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FileService fileService;

    @Autowired
    NoticeService noticeService;

    @GetMapping("count")
    public String Count(Model model, Map<String,Integer> map){
        UserExample userExample = new UserExample();
        int i = userService.countByExample(userExample);

        DeptExample deptExample = new DeptExample();
        int i1 = deptService.countByExample(deptExample);

        JobExample jobExample = new JobExample();
        int i2 = jobService.countByExample(jobExample);

        EmployeeExample employeeExample = new EmployeeExample();
        int i3 = employeeService.countByExample(employeeExample);

        UploadfileExample uploadfileExample = new UploadfileExample();
        int i4 = fileService.countByExample(uploadfileExample);

        NoticeExample noticeExample = new NoticeExample();
        int i5 = noticeService.countByExample(noticeExample);

        map.put("countuser",i);
        map.put("countdept",i1);
        map.put("countjob",i2);
        map.put("countemp",i3);
        map.put("countfile",i4);
        map.put("countnotice",i5);


        model.addAttribute(map);

        return "index/welcome.html";
    }
}
