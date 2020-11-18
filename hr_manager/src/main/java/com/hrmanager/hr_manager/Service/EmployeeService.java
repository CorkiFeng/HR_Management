package com.hrmanager.hr_manager.Service;



import com.hrmanager.hr_manager.Model.Employee;
import com.hrmanager.hr_manager.Model.EmployeeExample;

import java.util.List;

public interface EmployeeService {
    int countByExample(EmployeeExample example);

    int insert(Employee employee);

    int update(Employee employee);

    int deleteByPrimaryKey(int id);

    List<Employee> findAllEmployee();

    List<Employee> findSomeEmployee(String name, int jid, int did);

    List<Employee> Employee_remix(Employee employee);

    List<Employee> EmployeeByCondition(String name);

    int deleteByExample(EmployeeExample example);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectById(Integer id);
}
