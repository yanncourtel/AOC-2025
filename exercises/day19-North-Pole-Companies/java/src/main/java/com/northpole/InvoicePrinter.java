package com.northpole;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class InvoicePrinter {
    
    public String print(Invoice invoice, Map<String, ElfCompany> elfCompanies) {
        int totalAmount = 0;
        int loyaltyPoints = 0;
        StringBuilder result = new StringBuilder(
            String.format("Invoice for %s\n", invoice.customer));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        for (Delivery delivery : invoice.deliveries) {
            ElfCompany company = elfCompanies.get(delivery.companyID);
            int deliveryCost = calculateDeliveryCost(delivery, company);

            result.append(String.format(" %s: %s (%d packages)\n",
                    company.name,
                    currencyFormat.format(deliveryCost / 100.0),
                    delivery.packages));
            
            totalAmount += deliveryCost;
            loyaltyPoints += calculateLoyaltyPoints(delivery, company);
        }

        result.append(String.format("Amount owed is %s\n", 
                currencyFormat.format(totalAmount / 100.0)));
        result.append(String.format("You earned %d loyalty points\n", loyaltyPoints));
        return result.toString();
    }

    private int calculateDeliveryCost(Delivery delivery, ElfCompany company) {
        int cost = 0;
        switch (company.type) {
            case "express":
                cost = 50000;
                if (delivery.packages > 100) {
                    cost += 500 * (delivery.packages - 100);
                }
                break;
            case "standard":
                cost = 30000;
                if (delivery.packages > 50) {
                    cost += 1000 + 300 * (delivery.packages - 50);
                }
                cost += 200 * delivery.packages;
                break;
            default:
                throw new Error("unknown type: " + company.type);
        }
        return cost;
    }

    private int calculateLoyaltyPoints(Delivery delivery, ElfCompany company) {
        int points = Math.max(delivery.packages - 50, 0);
        if ("express".equals(company.type)) {
            points += (int) Math.floor(delivery.packages / 10);
        }
        return points;
    }
}
