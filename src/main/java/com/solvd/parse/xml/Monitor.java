package com.solvd.parse.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "monitor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Monitor {

    @XmlAttribute
    private int id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String model;
    @XmlAttribute
    private int hz;
    @XmlAttribute
    private String brand;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHz() {
        return hz;
    }

    public void setHz(int hz) {
        this.hz = hz;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", hz=" + hz +
                ", brand='" + brand + '\'' +
                '}';
    }
}
