package com.cascade.recyclerviewdecorations.adapter;

/**
 * Created by Salih Demir on 1.08.2017.
 */

public class Car {
    private String brand;
    private String model;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    String getBrand() {
        return brand;
    }

    String getModel() {
        return model;
    }
}