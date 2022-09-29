package com.solvd.beams;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute(name = "employee_id")
    private Long employee_id;
    @XmlAttribute(name = "employee_fullname")
    private String employee_fullname;
    @XmlAttribute(name = "employee_age")
    private int employee_age;
    @XmlAttribute(name = "employee_salary")
    private double employee_salary;
    @XmlAttribute(name = "employee_type")
    private String employee_type;
    @XmlAttribute(name = "boss_id")
    private int boss_id;

    public Employee(){}

    public Employee(String employee_fullname, int employee_age, double employee_salary, String employee_type, int boss_id) {
        this.employee_id = employee_id;
        this.employee_fullname = employee_fullname;
        this.employee_age = employee_age;
        this.employee_salary = employee_salary;
        this.employee_type = employee_type;
        this.boss_id = boss_id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_fullname() {
        return employee_fullname;
    }

    public void setEmployee_fullname(String employee_fullname) {
        this.employee_fullname = employee_fullname;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    public double getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(double employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }

    public int getBoss_id() {
        return boss_id;
    }

    public void setBoss_id(int boss_id) {
        this.boss_id = boss_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employee_fullname='" + employee_fullname + '\'' +
                ", employee_age=" + employee_age +
                ", employee_salary=" + employee_salary +
                ", employee_type='" + employee_type + '\'' +
                ", boss_id=" + boss_id +
                '}';
    }
}
