package com.northpole;

public class TaxRate {
    public final String name;
    public final double taxRate;
    public final String description;

    public TaxRate(String name, double taxRate, String description) {
        this.name = name;
        this.taxRate = taxRate;
        this.description = description;
    }
}
