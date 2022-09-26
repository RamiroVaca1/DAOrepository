package com.solvd.parse.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "monitors")
@XmlAccessorType(XmlAccessType.FIELD)
public class Monitors {

    @XmlElement(name = "monitor")
    private List<Monitor> monitors;

    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
    }
}
