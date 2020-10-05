package com.samir.has.api.object.livraison;

public class ChronoHomeDelivery implements Delivery {
    String ville;
    private String mode = "livraison à domicile chrono 24H ";

    public String getMode() {
        return mode+prix();
    }

    public ChronoHomeDelivery(String ville) {
        this.ville = ville;
    }

    @Override
    public double prix() {
        if(ville.equals("Paris"))
            return 6.99;
        return 9.99;
    }

    @Override
    public void livrer() {

    }

    @Override
    public void livrer(String adresse) {

    }
}
