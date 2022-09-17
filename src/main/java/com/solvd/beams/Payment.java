package com.solvd.beams;

import java.sql.Date;

public class Payment {
    private Long payment_id;
    private Date paymentDate;
    private double payment_amount;
    private int client_id;

    public Payment(){}

    public Payment(Long payment_id, Date paymentDate, double payment_amount, int client_id) {
        this.payment_id = payment_id;
        this.paymentDate = paymentDate;
        this.payment_amount = payment_amount;
        this.client_id = client_id;
    }

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id=" + payment_id +
                ", paymentDate=" + paymentDate +
                ", payment_amount=" + payment_amount +
                ", client_id=" + client_id +
                '}';
    }
}
