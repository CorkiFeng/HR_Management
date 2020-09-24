package com.controller;

import com.model.Dept;
import com.model.DeptExample;
import com.model.EmployeeExample;
import com.service.EmployeeService;
import com.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class DeptManagerController {

    String prefix = "index/";

    @Autowired
    IDeptService deptService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("query_all_depts")
    public String QueryAllDepts(Model model,Map<String,Object> map){
        List<Dept> depts = deptService.selectByAll();
        map.put("depts",depts);
        map.put("deptss",depts);
        model.addAttribute(map);
        return prefix+"query-dept";
    }

    @DeleteMapping("/delete_dept")
    public String DeleteDept(@RequestParam("id") Integer id){
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andDeptIdEqualTo(id);
        employeeService.deleteByExample(employeeExample);

        deptService.deleteByPrimaryKey(id);
        return "redirect:query_all_depts";
    }

    @PostMapping("add_dept")
    @ResponseBody
    public String AddDept(Dept dept){
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        criteria.andDnameEqualTo(dept.getDname());
        List<Dept> depts = deptService.selectByExample(deptExample);
        if (!depts.isEmpty()){
            return "-1";
        }
        int insert = deptService.insert(dept);
        if (insert == 1){
            return "1";
        }else{
            return "0";
        }
    }

    @GetMapping("/search_dept")
    public String SearchDept(Dept dept ,Model model, Map<String, Object> map){
        Integer did = dept.getDid();
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        criteria.andDidEqualTo(did);
        List<Dept> depts = deptService.selectByExample(deptExample);

        List<Dept> deptss = deptService.selectByAll();
        map.put("depts",depts);
        map.put("deptss",deptss);
        model.addAttribute(map);
        return prefix+"query-dept";
    }



}
