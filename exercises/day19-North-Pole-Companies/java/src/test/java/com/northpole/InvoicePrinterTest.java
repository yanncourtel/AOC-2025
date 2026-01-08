package com.northpole;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class InvoicePrinterTest {

    @Test
    void exampleInvoice() throws IOException {
        Map<String, ElfCompany> elfCompanies = loadElfCompanies();
        Invoice invoice = loadInvoice();
        InvoicePrinter printer = new InvoicePrinter();
        
        String result = printer.print(invoice, elfCompanies);
        
        Approvals.verify(result);
    }

    // TODO: Add exampleInvoiceWithTaxes() test here

    private Map<String, ElfCompany> loadElfCompanies() throws IOException {
        String json = Files.readString(Paths.get("src/test/resources/elfCompanies.json"));
        Gson gson = new Gson();
        Map<String, Map<String, String>> rawData = gson.fromJson(json, 
            new TypeToken<Map<String, Map<String, String>>>(){}.getType());
        
        Map<String, ElfCompany> companies = new HashMap<>();
        for (Map.Entry<String, Map<String, String>> entry : rawData.entrySet()) {
            Map<String, String> data = entry.getValue();
            companies.put(entry.getKey(), 
                new ElfCompany(data.get("name"), data.get("type"), data.get("region")));
        }
        return companies;
    }

    private Invoice loadInvoice() throws IOException {
        String json = Files.readString(Paths.get("src/test/resources/order.json"));
        Gson gson = new Gson();
        Map<String, Object> orderData = gson.fromJson(json, 
            new TypeToken<Map<String, Object>>(){}.getType());
        
        String customer = (String) orderData.get("customer");
        List<Map<String, Object>> deliveriesData = 
            (List<Map<String, Object>>) orderData.get("deliveries");
        
        List<Delivery> deliveries = new ArrayList<>();
        for (Map<String, Object> deliveryData : deliveriesData) {
            deliveries.add(new Delivery(
                (String) deliveryData.get("companyID"),
                ((Double) deliveryData.get("packages")).intValue()
            ));
        }
        return new Invoice(customer, deliveries);
    }
}
