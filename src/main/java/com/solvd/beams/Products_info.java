package com.solvd.beams;

public class Products_info {

    private Long product_info_id;
    private String product_description;
    private String product_brand;
    private int product_id;

    public Products_info(){}

    public Products_info(Long product_info_id, String product_description, String product_brand, int product_id) {
        this.product_info_id = product_info_id;
        this.product_description = product_description;
        this.product_brand = product_brand;
        this.product_id = product_id;
    }

    public Long getProduct_info_id() {
        return product_info_id;
    }

    public void setProduct_info_id(Long product_info_id) {
        this.product_info_id = product_info_id;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Products_info{" +
                "product_info_id=" + product_info_id +
                ", product_description='" + product_description + '\'' +
                ", product_brand='" + product_brand + '\'' +
                ", product_id=" + product_id +
                '}';
    }
}
