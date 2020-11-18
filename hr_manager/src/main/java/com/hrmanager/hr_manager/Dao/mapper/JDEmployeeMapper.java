package com.hrmanager.hr_manager.Dao.mapper;


import com.hrmanager.hr_manager.Model.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JDEmployeeMapper {

    List<Employee> findAllEmployee();

    List<Employee> findByEmployee(@Param("name") String name, @Param("jid") int jid, @Param("did") int did);
}
