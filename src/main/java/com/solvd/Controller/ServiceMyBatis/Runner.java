package com.solvd.Controller.ServiceMyBatis;

import com.solvd.beams.Employee;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();

        System.out.println("> Todos los registros ");
        List<Employee> lista = employeeService.getAll();
        lista.forEach((e) -> {
            System.out.println(e);
        });
/*
        System.out.println("> Lectura de registro id=1");
        Employee employee = employeeService.read(1L);
        System.out.println("> " +  employee);
        System.out.println("> Actualizando edad" );
        employee.setEmployee_age(28);
        employeeService.update(employee);

        System.out.println("> Eliminando registro id=2");
        employeeService.delete(2L);

        System.out.println("> creando nuevo registro ");
        employeeService.create(new Employee());

        lista = employeeService.getAll();
        lista.forEach((p) -> {
            System.out.println(p);
        });

 */
    }
}
