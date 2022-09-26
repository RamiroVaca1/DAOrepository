package com.solvd.parse.xml.jaxb;

import com.solvd.parse.xml.Monitor;
import com.solvd.parse.xml.Monitors;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;

public class Jaxb {

    public static void main(String[] args) throws JAXBException {
        marshall();
        unmarshall();
        marshallList();
        unmarshallList();
    }

    public static void marshall(){

        try {
            JAXBContext jb = JAXBContext.newInstance(Monitor.class);
            Marshaller ms = jb.createMarshaller();

            Monitor monitor = new Monitor();

            monitor.setId(1);
            monitor.setName("Tri-dimensional Monitor");
            monitor.setHz(144);
            monitor.setBrand("AOC");
            monitor.setModel("AA023LDMC24");

            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Marshal the cars list in console
            ms.marshal(monitor, System.out);

            //Marshal the cars list in file
            ms.marshal(monitor, new File("src/main/resources/marshalled_monitor.xml"));


        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unmarshall(){
        try {
            JAXBContext jb = JAXBContext.newInstance(Monitor.class);
            Unmarshaller ums = jb.createUnmarshaller();
            Monitor monitor = (Monitor) ums.unmarshal( new File("src/main/resources/marshalled_monitor.xml") );
            System.out.println(monitor);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void marshallList() throws JAXBException {
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

        //Marshalling
        JAXBContext jb = JAXBContext.newInstance(Monitors.class);
        Marshaller ms = jb.createMarshaller();

        ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        ms.marshal(monitors, System.out);


        ms.marshal(monitors, new File("src/main/resources/marshalled_monitorList.xml"));
    }

    public static void unmarshallList() throws JAXBException {
        JAXBContext jb = JAXBContext.newInstance(Monitors.class);
        Unmarshaller ums = jb.createUnmarshaller();

        //We had written this file in marshalling example
        Monitors monitors = (Monitors) ums.unmarshal(new File("src/main/resources/marshalled_monitorList.xml"));

        for (Monitor m : monitors.getMonitors()) {
            System.out.println(m);
        }
    }
}
