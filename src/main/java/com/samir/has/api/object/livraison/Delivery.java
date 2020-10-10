package com.samir.has.api.object.livraison;

public interface Delivery<T extends Delivery > {
    double price();
    String getModeString();
    void deliver();
    void deliver(String address);
    T getImplementedObject();
}
