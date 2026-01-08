package com.northpole

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.approvaltests.Approvals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class InvoicePrinterTest {

    @Test
    fun exampleInvoice() {
        val elfCompanies = loadElfCompanies()
        val invoice = loadInvoice()
        val printer = InvoicePrinter()

        val result = printer.print(invoice, elfCompanies)

        Approvals.verify(result)
    }

    // TODO: Add exampleInvoiceWithTaxes test here

    private fun loadElfCompanies(): Map<String, ElfCompany> {
        val json = Files.readString(Paths.get("src/test/resources/elfCompanies.json"))
        val gson = Gson()
        val type = object : TypeToken<Map<String, Map<String, String>>>() {}.type
        val rawData: Map<String, Map<String, String>> = gson.fromJson(json, type)

        return rawData.mapValues { (_, data) ->
            ElfCompany(
                name = data["name"]!!,
                type = data["type"]!!,
                region = data["region"]!!
            )
        }
    }

    private fun loadInvoice(): Invoice {
        val json = Files.readString(Paths.get("src/test/resources/order.json"))
        val gson = Gson()
        val type = object : TypeToken<Map<String, Any>>() {}.type
        val orderData: Map<String, Any> = gson.fromJson(json, type)

        val customer = orderData["customer"] as String
        val deliveriesData = orderData["deliveries"] as List<Map<String, Any>>

        val deliveries = deliveriesData.map { deliveryData ->
            Delivery(
                companyID = deliveryData["companyID"] as String,
                packages = (deliveryData["packages"] as Double).toInt()
            )
        }

        return Invoice(customer, deliveries)
    }
}
