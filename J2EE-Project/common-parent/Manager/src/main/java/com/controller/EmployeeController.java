package com.controller;

import com.model.*;
import com.service.EmployeeService;
import com.service.IDeptService;
import com.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private IDeptService iDeptService;

    @Autowired
    private IJobService iJobService;



    //增
    @PostMapping("add_employee")
    @ResponseBody
    public String AddEmp(Employee employee) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andNameEqualTo(employee.getName());
        List<Employee> employees = employeeService.selectByExample(employeeExample);
        if (!employees.isEmpty()){
            return "-1";
        }

        employee.setCreateDate(new Date());
        int insert = employeeService.insert(employee);
        if (insert == 1) {
            return "1";
        } else {
            return "0";
        }
    }


    @GetMapping("query_rd")
    public String QueryDeptAndRole(Model model,Map<String, Object> map) {

        //用于搜索
        List<Dept> depts = iDeptService.selectByAll();
        map.put("deptss",depts);

        List<Job> jobs = iJobService.selectByAll();
        map.put("jobss",jobs);

        model.addAttribute(map);
        return "index/add-worker";
    }

    //搜出所有
    @GetMapping("query_all_employees")
    public String QueryAllUsers(Model model,Map<String, Object> map) {

        //用于搜索
        List<Dept> depts = iDeptService.selectByAll();
        map.put("deptss",depts);

        List<Job> jobs = iJobService.selectByAll();
        map.put("jobss",jobs);

        Employee employee = new Employee();
        employee.setName("");
        employee.setDeptId(null);
        employee.setJobId(null);

        List<Employee> employeeRemixes = employeeService.Employee_remix(employee);
        map.put("employees",employeeRemixes);

        model.addAttribute(map);

        return "index/query-worker";
    }

    //条件搜
    @GetMapping("/search_employee")
    public String QuerySomeEmployee(Employee employee,
                                     Model model,Map<String, Object> map){

        System.out.println("==========================="+employee);

        //用于搜索
        List<Dept> depts = iDeptService.selectByAll();
        map.put("deptss",depts);

        List<Job> jobs = iJobService.selectByAll();
        map.put("jobss",jobs);

        employee.setName("%"+employee.getName()+"%");

        List<Employee> employees = employeeService.Employee_remix(employee);

//        List<Employee> employees = employeeService.EmployeeByCondition(employee.getName());

        map.put("employees",employees);

        model.addAttribute(map);

         return "index/query-worker";
    }

    //删
    @DeleteMapping("/delete_employee")
    public String DeleteUser(@RequestParam("id") Integer id) {
        employeeService.deleteByPrimaryKey(id);
        return "redirect:query_all_employees";
    }
}
