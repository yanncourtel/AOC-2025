package com.northpole

case class Invoice(customer: String, deliveries: List[Delivery])

case class Delivery(companyID: String, packages: Int)

case class ElfCompany(
  name: String,
  `type`: String,
  region: String
)

case class TaxRate(
  name: String,
  taxRate: Double,
  description: String
)
