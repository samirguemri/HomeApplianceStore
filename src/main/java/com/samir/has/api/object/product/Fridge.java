package com.samir.has.api.object.product;

public class Fridge extends Product {

    private int capacity;
    private boolean isFreezer;

    public Fridge(String type, String name, String description, double price, int availableStock,
                  int capacity, boolean isFreezer) {
        super(type, name, description, price, availableStock);
        this.capacity = capacity;
        this.isFreezer = isFreezer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFreezer() {
        return isFreezer;
    }

    public void setFreezer(boolean freezer) {
        this.isFreezer = freezer;
    }

    @Override
    public Fridge getImplementedObject() {
        return this;
    }
}
