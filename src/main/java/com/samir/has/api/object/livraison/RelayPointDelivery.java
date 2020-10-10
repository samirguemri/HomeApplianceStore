package com.samir.has.api.object.livraison;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class RelayPointDelivery implements Delivery {

    @XmlValue
    private final String modeString = "RelayPointDelivery";
    int relayPointNumber;

    public RelayPointDelivery(){}

    public RelayPointDelivery(int relayPointNumber) {
        this.relayPointNumber = relayPointNumber;
    }

    public String getModeString() {
        return modeString+ price();
    }

    @Override
    public double price() {
        if(relayPointNumber >= 1 && relayPointNumber <= 22)
            return 0;
        if(relayPointNumber >= 48)
            return 4.99;
        return 2.99;

    }

    @Override
    public RelayPointDelivery getImplementedObject() {
        return this;
    }
}
