package com.solvd.beams;

public class Employee_info {

    private Long employee_info_id;
    private String employee_street;
    private String employee_phone;
    private String employee_gender;
    private String employee_country;
    private String employee_city;
    private int employee_id;

    public Employee_info(){}

    public Employee_info(String employee_street, String employee_phone, String employee_gender, String employee_country, String employee_city, int employee_id) {
        this.employee_info_id = employee_info_id;
        this.employee_street = employee_street;
        this.employee_phone = employee_phone;
        this.employee_gender = employee_gender;
        this.employee_country = employee_country;
        this.employee_city = employee_city;
        this.employee_id = employee_id;
    }

    public Long getEmployee_info_id() {
        return employee_info_id;
    }

    public void setEmployee_info_id(Long employee_info_id) {
        this.employee_info_id = employee_info_id;
    }

    public String getEmployee_street() {
        return employee_street;
    }

    public void setEmployee_street(String employee_street) {
        this.employee_street = employee_street;
    }

    public String getEmployee_phone() {
        return employee_phone;
    }

    public void setEmployee_phone(String employee_phone) {
        this.employee_phone = employee_phone;
    }

    public String getEmployee_gender() {
        return employee_gender;
    }

    public void setEmployee_gender(String employee_gender) {
        this.employee_gender = employee_gender;
    }

    public String getEmployee_country() {
        return employee_country;
    }

    public void setEmployee_country(String employee_country) {
        this.employee_country = employee_country;
    }

    public String getEmployee_city() {
        return employee_city;
    }

    public void setEmployee_city(String employee_city) {
        this.employee_city = employee_city;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Employee_info{" +
                "employee_info_id=" + employee_info_id +
                ", employee_street='" + employee_street + '\'' +
                ", employee_phone='" + employee_phone + '\'' +
                ", employee_gender='" + employee_gender + '\'' +
                ", employee_country='" + employee_country + '\'' +
                ", employee_city='" + employee_city + '\'' +
                ", employee_id=" + employee_id +
                '}';
    }
}
