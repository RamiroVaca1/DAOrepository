package com.solvd.parse.xml.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.parse.xml.Monitor;
import com.solvd.parse.xml.Monitors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Jackson {
    public static void main(String[] args) throws IOException {
        serializeObject();
        deserializeObject();
        serializeObjectList();
        deserializeObjectList();
    }

    public static void serializeObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Monitor monitor = new Monitor();

        monitor.setId(1);
        monitor.setName("Tri-dimensional Monitor");
        monitor.setHz(144);
        monitor.setBrand("AOC");
        monitor.setModel("AA023LDMC24");

        objectMapper.writeValue(new File("src/main/resources/monitor.json"),monitor);
    }

    public static void deserializeObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Monitor monitor = objectMapper.readValue(new File("src/main/resources/monitor.json"), Monitor.class);
        System.out.println(monitor);
    }

    public static void serializeObjectList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Monitors monitors = new Monitors();
        monitors.setMonitors(new ArrayList<>());

        Monitor monitor = new Monitor();
        monitor.setId(2);
        monitor.setBrand("AOC");
        monitor.setModel("24LWOEM42");
        monitor.setHz(60);
        monitor.setName("DUOLIQUO");

        Monitor monitor2 = new Monitor();
        monitor2.setId(3);
        monitor2.setBrand("AOC");
        monitor2.setModel("24024LMAE2L1");
        monitor2.setHz(240);
        monitor2.setName("INSTANT");

        monitors.getMonitors().add(monitor);
        monitors.getMonitors().add(monitor2);

        objectMapper.writeValue(new File("src/main/resources/monitor_list.json"),monitors);
    }

    public static void deserializeObjectList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Monitors monitors = objectMapper.readValue(new File("src/main/resources/monitor_list.json"), Monitors.class);
        for (Monitor m : monitors.getMonitors()){
            System.out.println(m);
        }
    }
}
