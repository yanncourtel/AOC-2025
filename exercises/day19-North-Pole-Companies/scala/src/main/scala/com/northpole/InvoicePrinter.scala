package com.northpole

import java.text.NumberFormat
import java.util.Locale

class InvoicePrinter {
  def print(invoice: Invoice, elfCompanies: Map[String, ElfCompany]): String = {
    var totalAmount = 0
    var loyaltyPoints = 0
    val result = new StringBuilder(s"Invoice for ${invoice.customer}\n")
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

    for (delivery <- invoice.deliveries) {
      val company = elfCompanies(delivery.companyID)
      val deliveryCost = calculateDeliveryCost(delivery, company)

      result.append(s" ${company.name}: ${currencyFormat.format(deliveryCost / 100.0)} (${delivery.packages} packages)\n")
      
      totalAmount += deliveryCost
      loyaltyPoints += calculateLoyaltyPoints(delivery, company)
    }

    result.append(s"Amount owed is ${currencyFormat.format(totalAmount / 100.0)}\n")
    result.append(s"You earned $loyaltyPoints loyalty points\n")
    result.toString()
  }

  private def calculateDeliveryCost(delivery: Delivery, company: ElfCompany): Int = {
    var cost = 0
    company.`type` match {
      case "express" =>
        cost = 50000
        if (delivery.packages > 100) {
          cost += 500 * (delivery.packages - 100)
        }
      case "standard" =>
        cost = 30000
        if (delivery.packages > 50) {
          cost += 1000 + 300 * (delivery.packages - 50)
        }
        cost += 200 * delivery.packages
      case _ => throw new Error(s"unknown type: ${company.`type`}")
    }
    cost
  }

  private def calculateLoyaltyPoints(delivery: Delivery, company: ElfCompany): Int = {
    var points = Math.max(delivery.packages - 50, 0)
    if (company.`type` == "express") {
      points += Math.floor(delivery.packages / 10.0).toInt
    }
    points
  }
}
