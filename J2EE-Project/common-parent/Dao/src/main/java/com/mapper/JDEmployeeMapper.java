package com.mapper;

import com.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JDEmployeeMapper {

    List<Employee> findAllEmployee();

    List<Employee> findByEmployee(@Param("name") String name,@Param("jid") int jid,@Param("did") int did);
}
