package com.solvd.xml.ServiceJAXB;

import com.solvd.beams.Employee;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;

public class EmployeeJAXB {

    public static void main(String[] args) throws JAXBException {
        marshall();
        unmarshall();
        marshallList();
        unmarshallList();
    }

    public static void marshall(){

        try {
            JAXBContext jb = JAXBContext.newInstance(Employee.class);
            Marshaller ms = jb.createMarshaller();

            Employee employee = new Employee();

            employee.setEmployee_id(1L);
            employee.setEmployee_type("Cleaner");
            employee.setEmployee_salary(1700);
            employee.setEmployee_age(21);
            employee.setEmployee_fullname("Jorge Casanova");
            employee.setBoss_id(1);

            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Marshal the cars list in console
            ms.marshal(employee, System.out);

            //Marshal the cars list in file
            ms.marshal(employee, new File("src/main/resources/marshalled_employee.xml"));


        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unmarshall(){
        try {
            JAXBContext jb = JAXBContext.newInstance(Employee.class);
            Unmarshaller ums = jb.createUnmarshaller();
            Employee employee = (Employee) ums.unmarshal( new File("src/main/resources/marshalled_employee.xml") );
            System.out.println(employee);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void marshallList() throws JAXBException {
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

        //Marshalling
        JAXBContext jb = JAXBContext.newInstance(Employees.class);
        Marshaller ms = jb.createMarshaller();

        ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        ms.marshal(employees, System.out);


        ms.marshal(employees, new File("src/main/resources/marshalled_employeeList.xml"));
    }

    public static void unmarshallList() throws JAXBException {
        JAXBContext jb = JAXBContext.newInstance(Employees.class);
        Unmarshaller ums = jb.createUnmarshaller();

        //We had written this file in marshalling example
        Employees employees = (Employees) ums.unmarshal(new File("src/main/resources/marshalled_employeeList.xml"));

        for (Employee e : employees.getEmployees()) {
            System.out.println(e);
        }
    }
}
