package com.samir.has.api.object.product;

public class CoffeeMachine extends Product{

    private String coffeeType;

    public CoffeeMachine(String productType, String name, String description, double price, int availableStock, String coffeeType) {
        super(productType, name, description, price, availableStock);
        this.coffeeType = coffeeType;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    @Override
    public CoffeeMachine getImplementedObject() {
        return this;
    }
}
