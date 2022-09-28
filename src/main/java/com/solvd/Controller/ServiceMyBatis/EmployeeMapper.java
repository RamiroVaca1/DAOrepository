package com.solvd.Controller.ServiceMyBatis;

import com.solvd.beams.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {

    public static void main(String[] args) {
        
    }

    @Select("select * from employee")
    List<Employee> getAll();
}
