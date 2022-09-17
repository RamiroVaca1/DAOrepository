package com.solvd.beams;

public class Boss {
    private Long boss_id;
    private String boss_fullname;
    private int boss_age;
    private double boss_salary;
    private String boss_section;

    public Boss(){}

    public Boss(String boss_fullname, int boss_age, double boss_salary, String boss_section) {
        this.boss_fullname = boss_fullname;
        this.boss_age = boss_age;
        this.boss_salary = boss_salary;
        this.boss_section = boss_section;
    }

    public Long getBoss_id() {
        return boss_id;
    }

    public void setBoss_id(Long boss_id) {
        this.boss_id = boss_id;
    }

    public String getBoss_fullname() {
        return boss_fullname;
    }

    public void setBoss_fullname(String boss_fullname) {
        this.boss_fullname = boss_fullname;
    }

    public int getBoss_age() {
        return boss_age;
    }

    public void setBoss_age(int boss_age) {
        this.boss_age = boss_age;
    }

    public double getBoss_salary() {
        return boss_salary;
    }

    public void setBoss_salary(double boss_salary) {
        this.boss_salary = boss_salary;
    }

    public String getBoss_section() {
        return boss_section;
    }

    public void setBoss_section(String boss_section) {
        this.boss_section = boss_section;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "boss_id=" + boss_id +
                ", boss_fullname='" + boss_fullname + '\'' +
                ", boss_age=" + boss_age +
                ", boss_salary=" + boss_salary +
                ", boss_section='" + boss_section + '\'' +
                '}';
    }
}
