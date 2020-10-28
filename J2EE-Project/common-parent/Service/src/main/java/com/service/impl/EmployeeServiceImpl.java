package com.service.impl;

import com.mapper.EmployeeMapper;
import com.mapper.JDEmployeeMapper;
import com.model.Employee;
import com.model.EmployeeExample;
import com.model.EmployeeRemix;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private JDEmployeeMapper jdemployeeMapper;

    @Override
    public int countByExample(EmployeeExample example) {
        return employeeMapper.countByExample(example);
    }

    @Override
    public int insert(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeMapper.updateByPrimaryKey(employee);
    }
    @Override
    public int deleteByPrimaryKey(int id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return jdemployeeMapper.findAllEmployee();
    }

    @Override
    public List<Employee> findSomeEmployee(String name, int jid, int did) {
        return jdemployeeMapper.findByEmployee(name,jid,did);
    }

    @Override
    public List<Employee> Employee_remix(Employee employee) {
        return employeeMapper.Employee_remix(employee);
    }


    @Override
    public List<Employee> EmployeeByCondition(String name) {
        return employeeMapper.EmployeeByCondition(name);
    }




    @Override
    public int deleteByExample(EmployeeExample example) {
        return employeeMapper.deleteByExample(example);
    }

    @Override
    public List<Employee> selectByExample(EmployeeExample example) {
        return employeeMapper.selectByExample(example);
    }

    @Override
    public Employee selectById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

}
