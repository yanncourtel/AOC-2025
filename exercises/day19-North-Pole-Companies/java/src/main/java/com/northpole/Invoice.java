package com.northpole;

import java.util.List;

public class Invoice {
    public final String customer;
    public final List<Delivery> deliveries;

    public Invoice(String customer, List<Delivery> deliveries) {
        this.customer = customer;
        this.deliveries = deliveries;
    }
}
