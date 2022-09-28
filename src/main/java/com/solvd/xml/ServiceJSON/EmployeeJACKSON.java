package com.solvd.xml.ServiceJSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.xml.ServiceJAXB.Employees;
import com.solvd.beams.Employee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeJACKSON {

    public static void main(String[] args) throws IOException {
        serializeObject();
        deserializeObject();
        serializeObjectList();
        deserializeObjectList();
    }

    public static void serializeObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Employee employee = new Employee();

        employee.setEmployee_id(1L);
        employee.setEmployee_type("Cleaner");
        employee.setEmployee_salary(1700);
        employee.setEmployee_age(21);
        employee.setEmployee_fullname("Jorge Casanova");
        employee.setBoss_id(1);

        objectMapper.writeValue(new File("src/main/resources/employee.json"),employee);
    }

    public static void deserializeObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(new File("src/main/resources/employee.json"), Employee.class);
        System.out.println(employee);
    }

    public static void serializeObjectList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employees employees = new Employees();
        employees.setEmployees(new ArrayList<>());

        Employee employee = new Employee();

        employee.setEmployee_id(1L);
        employee.setEmployee_type("Cleaner");
        employee.setEmployee_salary(1700);
        employee.setEmployee_age(21);
        employee.setEmployee_fullname("Jorge Casanova");
        employee.setBoss_id(1);

        Employee employee1 = new Employee();
        employee.setEmployee_id(2L);
        employee.setEmployee_type("Cashier");
        employee.setEmployee_salary(2000);
        employee.setEmployee_age(25);
        employee.setEmployee_fullname("Kilian Mbappe");
        employee.setBoss_id(2);

        employees.getEmployees().add(employee);
        employees.getEmployees().add(employee1);

        objectMapper.writeValue(new File("src/main/resources/employee_list.json"),employees);
    }

    public static void deserializeObjectList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employees employees = objectMapper.readValue(new File("src/main/resources/employee_list.json"), Employees.class);
        for (Employee e : employees.getEmployees()){
            System.out.println(e);
        }
    }

}
