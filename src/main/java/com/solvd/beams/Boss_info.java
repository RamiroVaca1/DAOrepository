package com.solvd.beams;

public class Boss_info {
    private Long boss_info_id;
    private String boss_street;
    private String boss_phone;
    private String boss_gender;
    private String boss_country;
    private String boss_city;
    private int boss_id;



    public Boss_info(){}

    public Boss_info(String boss_street, String boss_phone, String boss_gender, String boss_country, String boss_city, int boss_id) {
        this.boss_street = boss_street;
        this.boss_phone = boss_phone;
        this.boss_gender = boss_gender;
        this.boss_country = boss_country;
        this.boss_city = boss_city;
        this.boss_id = boss_id;
    }

    public Long getBoss_info_id() {
        return boss_info_id;
    }

    public void setBoss_info_id(Long boss_info_id) {
        this.boss_info_id = boss_info_id;
    }

    public String getBoss_street() {
        return boss_street;
    }

    public void setBoss_street(String boss_street) {
        this.boss_street = boss_street;
    }

    public String getBoss_phone() {
        return boss_phone;
    }

    public void setBoss_phone(String boss_phone) {
        this.boss_phone = boss_phone;
    }

    public String getBoss_gender() {
        return boss_gender;
    }

    public void setBoss_gender(String boss_gender) {
        this.boss_gender = boss_gender;
    }

    public String getBoss_country() {
        return boss_country;
    }

    public void setBoss_country(String boss_country) {
        this.boss_country = boss_country;
    }

    public String getBoss_city() {
        return boss_city;
    }

    public void setBoss_city(String boss_city) {
        this.boss_city = boss_city;
    }

    public int getBoss_id() {
        return boss_id;
    }

    public void setBoss_id(int boss_id) {
        this.boss_id = boss_id;
    }

    @Override
    public String toString() {
        return "Boss_info{" +
                "boss_info_id=" + boss_info_id +
                ", boss_street='" + boss_street + '\'' +
                ", boss_phone='" + boss_phone + '\'' +
                ", boss_gender='" + boss_gender + '\'' +
                ", boss_country='" + boss_country + '\'' +
                ", boss_city='" + boss_city + '\'' +
                ", boss_id=" + boss_id +
                '}';
    }
}
