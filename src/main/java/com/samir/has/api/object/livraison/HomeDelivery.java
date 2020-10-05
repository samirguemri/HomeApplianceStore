package com.samir.has.api.object.livraison;

public class HomeDelivery implements Delivery {
    private String mode = "livraison à domicile 4.99€";

    @Override
    public String getMode() {
        return mode;
    }

    @Override
    public double prix() {
        return 4.99;
    }

    @Override
    public void livrer() {

    }

    @Override
    public void livrer(String adresse) {

    }
}
