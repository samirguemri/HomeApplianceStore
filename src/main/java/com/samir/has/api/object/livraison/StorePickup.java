package com.samir.has.api.object.livraison;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class StorePickup implements Delivery {

    @XmlValue
    private final String modeString = "StorePickup";

    public String getModeString() {
        return modeString;
    }

    @Override
    public double price() {
        return 0;
    }

    @Override
    public StorePickup getImplementedObject() {
        return this;
    }
}
