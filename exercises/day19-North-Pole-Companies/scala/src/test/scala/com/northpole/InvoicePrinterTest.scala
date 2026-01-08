package com.northpole

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.approvaltests.Approvals
import org.scalatest.funsuite.AnyFunSuite

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters._

class InvoicePrinterTest extends AnyFunSuite {

  test("exampleInvoice") {
    val elfCompanies = loadElfCompanies()
    val invoice = loadInvoice()
    val printer = new InvoicePrinter()

    val result = printer.print(invoice, elfCompanies)

    Approvals.verify(result)
  }

  // TODO: Add exampleInvoiceWithTaxes test here

  private def loadElfCompanies(): Map[String, ElfCompany] = {
    val json = Files.readString(Paths.get("src/test/resources/elfCompanies.json"))
    val gson = new Gson()
    val typeToken = new TypeToken[java.util.Map[String, java.util.Map[String, String]]]() {}
    val rawData: java.util.Map[String, java.util.Map[String, String]] = 
      gson.fromJson(json, typeToken.getType)

    rawData.asScala.map { case (key, data) =>
      key -> ElfCompany(
        name = data.get("name"),
        `type` = data.get("type"),
        region = data.get("region")
      )
    }.toMap
  }

  private def loadInvoice(): Invoice = {
    val json = Files.readString(Paths.get("src/test/resources/order.json"))
    val gson = new Gson()
    val typeToken = new TypeToken[java.util.Map[String, Object]]() {}
    val orderData: java.util.Map[String, Object] = gson.fromJson(json, typeToken.getType)

    val customer = orderData.get("customer").asInstanceOf[String]
    val deliveriesData = orderData.get("deliveries")
      .asInstanceOf[java.util.List[java.util.Map[String, Object]]]

    val deliveries = deliveriesData.asScala.map { deliveryData =>
      Delivery(
        companyID = deliveryData.get("companyID").asInstanceOf[String],
        packages = deliveryData.get("packages").asInstanceOf[Double].toInt
      )
    }.toList

    Invoice(customer, deliveries)
  }
}
