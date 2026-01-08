import { Invoice } from './Invoice';
import { ElfCompany } from './ElfCompany';

export class InvoicePrinter {
  print(invoice: Invoice, elfCompanies: Map<string, ElfCompany>): string {
    let totalAmount = 0;
    let loyaltyPoints = 0;
    let result = `Invoice for ${invoice.customer}\n`;
    const format = new Intl.NumberFormat("en-US", {
      style: "currency",
      currency: "USD",
      minimumFractionDigits: 2
    }).format;

    for (const delivery of invoice.deliveries) {
      const company = elfCompanies.get(delivery.companyID)!;
      const deliveryCost = this.calculateDeliveryCost(delivery, company);

      result += ` ${company.name}: ${format(deliveryCost / 100)} (${delivery.packages} packages)\n`;
      
      totalAmount += deliveryCost;
      loyaltyPoints += this.calculateLoyaltyPoints(delivery, company);
    }

    result += `Amount owed is ${format(totalAmount / 100)}\n`;
    result += `You earned ${loyaltyPoints} loyalty points\n`;
    return result;
  }

  private calculateDeliveryCost(delivery: any, company: ElfCompany): number {
    let cost = 0;
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
        throw new Error(`unknown type: ${company.type}`);
    }
    return cost;
  }

  private calculateLoyaltyPoints(delivery: any, company: ElfCompany): number {
    let points = Math.max(delivery.packages - 50, 0);
    if (company.type === "express") {
      points += Math.floor(delivery.packages / 10);
    }
    return points;
  }
}
