package com.samir.has.api.object.livraison;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class HomeDelivery implements Delivery {

    @XmlValue
    private final String modeString = "HomeDelivery";

    @Override
    public String getModeString() {
        return modeString;
    }

    @Override
    public double price() {
        return 4.99;
    }

    @Override
    public HomeDelivery getImplementedObject() {
        return this;
    }

}
