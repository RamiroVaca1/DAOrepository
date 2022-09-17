package com.solvd.beams;

import java.sql.Date;

public class Orders {

    private Long order_id;
    private Date order_date;
    private String order_status;
    private int client_id;

    public Orders(){}

    public Orders(Long order_id, Date order_date, String order_status, int client_id) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_status = order_status;
        this.client_id = client_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_id=" + order_id +
                ", order_date=" + order_date +
                ", order_status='" + order_status + '\'' +
                ", client_id=" + client_id +
                '}';
    }
}
