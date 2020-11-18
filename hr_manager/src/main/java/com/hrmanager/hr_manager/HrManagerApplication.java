package com.hrmanager.hr_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.hrmanager.hr_manager.Dao.mapper"})
public class HrManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrManagerApplication.class, args);
    }

}
