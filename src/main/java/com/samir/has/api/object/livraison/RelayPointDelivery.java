package com.samir.has.api.object.livraison;

public class RelayPointDelivery implements Delivery {
    int nbPointRelais;
    private String mode = "livraison point relais ";

    public String getMode() {
        return mode+prix();
    }

    public RelayPointDelivery(int nbPointRelais) {
        this.nbPointRelais = nbPointRelais;
    }

    @Override
    public double prix() {
        if(nbPointRelais >= 1 && nbPointRelais <= 22)
            return 0;
        if(nbPointRelais >= 48)
            return 4.99;
        return 2.99;

    }

    @Override
    public void livrer() {

    }

    @Override
    public void livrer(String adresse) {

    }
}
