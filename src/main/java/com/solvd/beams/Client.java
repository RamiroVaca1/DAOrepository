package com.solvd.beams;

public class Client {

    private Long client_id;
    private String client_fullname;
    private String client_phone;
    private String client_address;
    private String client_city;
    private String client_country;

    public Client(){}

    public Client(String client_fullname, String client_phone, String client_address, String client_city, String client_country) {
        this.client_fullname = client_fullname;
        this.client_phone = client_phone;
        this.client_address = client_address;
        this.client_city = client_city;
        this.client_country = client_country;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getClient_fullname() {
        return client_fullname;
    }

    public void setClient_fullname(String client_fullname) {
        this.client_fullname = client_fullname;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getClient_city() {
        return client_city;
    }

    public void setClient_city(String client_city) {
        this.client_city = client_city;
    }

    public String getClient_country() {
        return client_country;
    }

    public void setClient_country(String client_country) {
        this.client_country = client_country;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", client_fullname='" + client_fullname + '\'' +
                ", client_phone='" + client_phone + '\'' +
                ", client_address='" + client_address + '\'' +
                ", client_city='" + client_city + '\'' +
                ", client_country='" + client_country + '\'' +
                '}';
    }
}
