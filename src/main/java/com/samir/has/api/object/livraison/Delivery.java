package com.samir.has.api.object.livraison;

public interface Delivery {
    double prix();
    String getMode();
    void livrer ();
    void livrer(String adresse);
}
