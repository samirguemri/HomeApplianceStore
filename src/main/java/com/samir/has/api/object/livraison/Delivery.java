package com.samir.has.api.object.livraison;

public interface Delivery<T extends Delivery > {
    double price();
    String getModeString();
    T getImplementedObject();
}
