package com.solvd.beams;

public class Products {

    private Long product_id;
    private String product_name;
    private String product_quantity;
    private double product_price;

    public Products(){}

    public Products(Long product_id, String product_name, String product_quantity, double product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_quantity='" + product_quantity + '\'' +
                ", product_price=" + product_price +
                '}';
    }
}
