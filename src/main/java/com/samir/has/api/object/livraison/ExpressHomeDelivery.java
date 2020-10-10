package com.samir.has.api.object.livraison;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class ExpressHomeDelivery implements Delivery {

    @XmlValue
    private final String modeString = "ExpressHomeDelivery";
    private final static String CITY_REFERENCE = "Paris";
    String city;

    public ExpressHomeDelivery(){}
    public ExpressHomeDelivery(String city) {
        this.city = city;
    }

    public String getModeString() {
        return modeString+ price();
    }

    @Override
    public double price() {
        if(city.equals(CITY_REFERENCE))
            return 6.99;
        return 9.99;
    }

    @Override
    public void deliver() {}

    @Override
    public void deliver(String address) {}

    @Override
    public ExpressHomeDelivery getImplementedObject() {
        return this;
    }

}
