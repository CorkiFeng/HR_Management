package com.hrmanager.hr_manager.Manager.controller;


import com.hrmanager.hr_manager.Model.Dept;
import com.hrmanager.hr_manager.Model.Employee;
import com.hrmanager.hr_manager.Model.EmployeeExample;
import com.hrmanager.hr_manager.Model.Job;
import com.hrmanager.hr_manager.Service.EmployeeService;
import com.hrmanager.hr_manager.Service.IDeptService;
import com.hrmanager.hr_manager.Service.IJobService;
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
    public String QueryDeptAndRole(Model model, Map<String, Object> map) {

//        System.out.println("=================================");
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
    public String QueryAllUsers(Model model, Map<String, Object> map) {

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
                                    Model model, Map<String, Object> map){

//        System.out.println("==========================="+employee);

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

    @PostMapping("/modify_emp")
    @ResponseBody
    public String ModifyEmp(Employee employee){
        System.out.println("=========================="+employee.toString());
        int insert = employeeService.update(employee);
        if (insert == 1) {
            return "1";
        } else {
            return "0";
        }
    }

    @GetMapping("/getemp/{id}")
    public String GetEmp(@PathVariable("id") Integer id, Map<String, Object> map, Model model){
        Employee employee = employeeService.selectById(id);
        List<Dept> depts = iDeptService.selectByAll();
        map.put("deptss",depts);

        List<Job> jobs = iJobService.selectByAll();
        map.put("jobss",jobs);

        map.put("employee",employee);

        model.addAttribute(map);
        return "index/add-worker";


    }

//    @PostMapping("/getemp")
//    @ResponseBody
//    public String GetEmp(@RequestParam("id") Integer id){
//        Employee employee = employeeService.selectById(id);
//        Dept dept = iDeptService.selectByPrimaryKey(employee.getDeptId());
//        Job job = iJobService.selectByPrimaryKey(employee.getJobId());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jo = new JSONObject();
//        jo.put("id", employee.getId());
//        jo.put("did",employee.getDeptId());
//        jo.put("dname", dept.getDname());
//        jo.put("jid",employee.getJobId());
//        jo.put("jname", job.getJname());
//        jo.put("name", employee.getName());
//        jo.put("card_id", employee.getCardId());
//        jo.put("address", employee.getAddress());
//        jo.put("post_code", employee.getPostCode());
//        jo.put("tel", employee.getTel());
//        jo.put("phone", employee.getPhone());
//        jo.put("qq_num", employee.getQqNum());
//        jo.put("email", employee.getEmail());
//        jo.put("sex", employee.getSex());
//        jo.put("party", employee.getParty());
//        jo.put("birthday", format.format(employee.getBirthday()));
//        jo.put("races", employee.getRace());
//        jo.put("education", employee.getEducation());
//        jo.put("speciality", employee.getSpeciality());
//        jo.put("hobby", employee.getHobby());
//        jo.put("remark", employee.getRemark());
//        jsonArray.add(jo);
//        return jsonArray.toString();
//
//    }
}
