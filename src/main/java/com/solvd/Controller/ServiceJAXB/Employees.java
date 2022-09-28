package com.solvd.Controller.ServiceJAXB;

import com.solvd.beams.Employee;
import jakarta.xml.bind.annotation.*;

import java.util.List;
@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {
    @XmlElement (name = "employee")
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
