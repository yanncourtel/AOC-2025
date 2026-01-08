package com.northpole

data class Invoice(
    val customer: String,
    val deliveries: List<Delivery>
)

data class Delivery(
    val companyID: String,
    val packages: Int
)

data class ElfCompany(
    val name: String,
    val type: String,
    val region: String
)

data class TaxRate(
    val name: String,
    val taxRate: Double,
    val description: String
)
