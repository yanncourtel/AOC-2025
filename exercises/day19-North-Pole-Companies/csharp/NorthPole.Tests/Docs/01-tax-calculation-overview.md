# Tax Calculation Feature - Overview

**Date:** 2026-01-08  
**Status:** Planning Phase  
**Project:** NorthPole Invoice System

---

## Purpose

Add regional tax calculation capabilities to the invoice printing system. The system should calculate and display taxes based on the delivery company's region, showing itemized tax per delivery and aggregate tax totals.

---

## Business Requirements

### Functional Requirements
- Calculate tax based on delivery company region
- Display tax per delivery line item
- Show subtotal (pre-tax amount)
- Show total tax amount
- Display final amount owed (subtotal + taxes)
- Maintain existing cost and loyalty point calculations

### Non-Functional Requirements
- Maintain backwards compatibility with non-tax invoices
- Use approval testing for regression protection
- Keep code maintainable and testable
- Use tactical refactoring approach

---

## Tax Rates by Region

| Region      | Tax Rate | Description      |
|-------------|----------|------------------|
| North Pole  | 0%       | Tax-free zone    |
| Nordic      | 15%      | VAT applies      |
| Alpine      | 20%      | Regional tax     |
| Arctic      | 10%      | Arctic tax zone  |

---

## Output Format Changes

### An example - Before (No Tax)

```Invoice for Toys-R-Us North America
Rudolph Express Delivery: $600.00 (120 packages)
Jingle's Standard Service: $560.00 (80 packages)
Frosty's Fast Fleet: $500.00 (95 packages)
Amount owed is $1,660.00
You earned 166 loyalty points
```

### Another example - After (With Taxes)

```Invoice for Toys-R-Us North America
Invoice for Toys-R-Us North America
 Rudolph Express Delivery: $600.00 (120 packages)
   Tax (North Pole - 0%): $0.00
 Jingle's Standard Service: $960.00 (160 packages)
   Tax (Nordic Region - 15%): $144.00
 Frosty's Fast Fleet: $725.00 (145 packages)
   Tax (Alpine Region - 20%): $145.00
Subtotal: $2,285.00
Total Tax: $289.00
Amount owed is $2,574.00
You earned 301 loyalty points
```
