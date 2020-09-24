package com.controller;

import com.model.EmployeeExample;
import com.model.Job;
import com.model.JobExample;
import com.service.EmployeeService;
import com.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class WorkManagerController {

    String prefix = "index/";

    @Autowired
    IJobService jobService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("query_all_work")
    public String QueryAllJobs(Model model,Map<String, Object> map){
        List<Job> jobs = jobService.selectByAll();
        map.put("jobs",jobs);
        map.put("jobss",jobs);
        model.addAttribute(map);
        return prefix+"query-work";
    }

    @DeleteMapping("/delete_job")
    public String DeleteJob(@RequestParam("id") Integer id){
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andJobIdEqualTo(id);
        employeeService.deleteByExample(employeeExample);
        jobService.deleteByPrimaryKey(id);
        return "redirect:query_all_work";
    }

    @PostMapping("add_job")
    @ResponseBody
    public String AddJob(Job job){
        JobExample jobExample = new JobExample();
        JobExample.Criteria criteria = jobExample.createCriteria();
        criteria.andJnameEqualTo(job.getJname());
        List<Job> jobs = jobService.selectByExample(jobExample);
        if (!jobs.isEmpty()){
            return "-1";
        }
        int insert = jobService.insert(job);
        if (insert == 1){
            return "1";
        }else{
            return "0";
        }
    }

    @GetMapping("/search_job")
    public String SearchDept(Job job, Model model, Map<String, Object> map){
        Integer jid = job.getJid();
        JobExample jobExample = new JobExample();
        JobExample.Criteria criteria = jobExample.createCriteria();
        criteria.andJidEqualTo(jid);
        List<Job> jobs = jobService.selectByExample(jobExample);

        List<Job> jobss = jobService.selectByAll();
        map.put("jobs",jobs);
        map.put("jobss",jobss);
        model.addAttribute(map);
        return prefix+"query-work";
    }

}
