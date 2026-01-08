using System;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Collections.Generic;

namespace NorthPole
{
    
    public class InvoicePrinter
    {
        private static readonly Dictionary<string, (string Name, double Rate)> TaxRates = new()
        {
            ["north-pole"] = ("North Pole", 0.0),
            ["nordic"] = ("Nordic Region", 0.15),
            ["alpine"] = ("Alpine Region", 0.20),
            ["arctic"] = ("Arctic Region", 0.10)
        };
        
        public string Print(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies)
        {
            var totalAmount = 0;
            var loyaltyPoints = 0;
            var result = new StringBuilder($"Invoice for {invoice.Customer}\n");
            var currencyFormat = new CultureInfo("en-US");

            foreach (var delivery in invoice.Deliveries)
            {
                var company = elfCompanies[delivery.CompanyID];
                var deliveryCost = CalculateDeliveryCost(delivery, company);

                result.AppendLine($" {company.Name}: {(deliveryCost / 100.0).ToString("C", currencyFormat)} ({delivery.Packages} packages)");
                
                totalAmount += deliveryCost;
                loyaltyPoints += CalculateLoyaltyPoints(delivery, company);
            }

            result.AppendLine($"Amount owed is {(totalAmount / 100.0).ToString("C", currencyFormat)}");
            result.AppendLine($"You earned {loyaltyPoints} loyalty points");
            return result.ToString();
        }
        
        public string PrintWithTaxes(Invoice invoice, Dictionary<string, ElfCompany> elfCompanies)
        {
            var subtotal = 0;
            var totalTax = 0;
            var loyaltyPoints = 0;
            var result = new StringBuilder($"Invoice for {invoice.Customer}\n");
            var currencyFormat = new CultureInfo("en-US");

            foreach (var delivery in invoice.Deliveries)
            {
                var company = elfCompanies[delivery.CompanyID];
                var deliveryCost = CalculateDeliveryCost(delivery, company);
                var tax = CalculateTax(deliveryCost, company.Region);

                result.AppendLine($" {company.Name}: {(deliveryCost / 100.0).ToString("C", currencyFormat)} ({delivery.Packages} packages)");
                
                var taxInfo = TaxRates[company.Region];
                result.AppendLine($"   Tax ({taxInfo.Name} - {taxInfo.Rate:P0}): {(tax / 100.0).ToString("C", currencyFormat)}");
                
                subtotal += deliveryCost;
                totalTax += tax;
                loyaltyPoints += CalculateLoyaltyPoints(delivery, company);
            }
            
            result.AppendLine($"Subtotal: {(subtotal / 100.0).ToString("C", currencyFormat)}");
            result.AppendLine($"Total Tax: {(totalTax / 100.0).ToString("C", currencyFormat)}");
            result.AppendLine($"Amount owed is {((subtotal + totalTax) / 100.0).ToString("C", currencyFormat)}");
            result.AppendLine($"You earned {loyaltyPoints} loyalty points");
            
            return result.ToString();
        }

        private int CalculateTax(int cost, string region)
        {
            if (!TaxRates.TryGetValue(region, out var taxInfo))
            {
                throw new Exception($"Unknown region: {region}");
            }
            
            return (int)(cost * taxInfo.Rate);
        }

        private int CalculateDeliveryCost(Delivery delivery, ElfCompany company)
        {
            var cost = 0;
            switch (company.Type)
            {
                case "express":
                    cost = 50000;
                    if (delivery.Packages > 100)
                    {
                        cost += 500 * (delivery.Packages - 100);
                    }
                    break;
                case "standard":
                    cost = 30000;
                    if (delivery.Packages > 50)
                    {
                        cost += 1000 + 300 * (delivery.Packages - 50);
                    }
                    cost += 200 * delivery.Packages;
                    break;
                default:
                    throw new Exception($"unknown type: {company.Type}");
            }
            return cost;
        }

        private int CalculateLoyaltyPoints(Delivery delivery, ElfCompany company)
        {
            var points = Math.Max(delivery.Packages - 50, 0);
            if (company.Type == "express")
            {
                points += (int)Math.Floor(delivery.Packages / 10.0);
            }
            return points;
        }
    }
}
