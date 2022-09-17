package com.solvd.beams;

public class Order_details {
    private Long order_id;
    private Long product_id;
    private int order_quantity;
    private double order_price;

    public Order_details(){}

    public Order_details(Long order_id, Long product_id, int order_quantity, double order_price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.order_quantity = order_quantity;
        this.order_price = order_price;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity = order_quantity;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    @Override
    public String toString() {
        return "Order_details{" +
                "order_id=" + order_id +
                ", product_id=" + product_id +
                ", order_quantity=" + order_quantity +
                ", order_price=" + order_price +
                '}';
    }
}
