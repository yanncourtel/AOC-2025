package com.northpole

import java.text.NumberFormat
import java.util.Locale

class InvoicePrinter {
    fun print(invoice: Invoice, elfCompanies: Map<String, ElfCompany>): String {
        var totalAmount = 0
        var loyaltyPoints = 0
        val result = StringBuilder("Invoice for ${invoice.customer}\n")
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

        for (delivery in invoice.deliveries) {
            val company = elfCompanies[delivery.companyID]!!
            val deliveryCost = calculateDeliveryCost(delivery, company)

            result.append(" ${company.name}: ${currencyFormat.format(deliveryCost / 100.0)} (${delivery.packages} packages)\n")
            
            totalAmount += deliveryCost
            loyaltyPoints += calculateLoyaltyPoints(delivery, company)
        }

        result.append("Amount owed is ${currencyFormat.format(totalAmount / 100.0)}\n")
        result.append("You earned $loyaltyPoints loyalty points\n")
        return result.toString()
    }

    private fun calculateDeliveryCost(delivery: Delivery, company: ElfCompany): Int {
        var cost = 0
        when (company.type) {
            "express" -> {
                cost = 50000
                if (delivery.packages > 100) {
                    cost += 500 * (delivery.packages - 100)
                }
            }
            "standard" -> {
                cost = 30000
                if (delivery.packages > 50) {
                    cost += 1000 + 300 * (delivery.packages - 50)
                }
                cost += 200 * delivery.packages
            }
            else -> throw Error("unknown type: ${company.type}")
        }
        return cost
    }

    private fun calculateLoyaltyPoints(delivery: Delivery, company: ElfCompany): Int {
        var points = maxOf(delivery.packages - 50, 0)
        if (company.type == "express") {
            points += (delivery.packages / 10)
        }
        return points
    }
}
