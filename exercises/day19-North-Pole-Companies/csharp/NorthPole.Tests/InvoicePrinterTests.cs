using ApprovalTests;
using ApprovalTests.Reporters;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Xunit;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace NorthPole.Tests
{
    [UseReporter(typeof(DiffReporter))]
    public class InvoicePrinterTests
    {
        private const string ResourcesOrder = "Resources/order.json";
        private const string ResourcesOrderWithTaxes = "Resources/orderWithTaxes.json";

        private static InvoicePrinter CreateInvoicePrinter() => new();

        [Fact]
        public void ExampleInvoice()
        {
            var elfCompanies = LoadElfCompanies();
            var invoice = LoadInvoice(ResourcesOrder);

            var result = CreateInvoicePrinter() 
                .Print(invoice, elfCompanies);

            Approvals.Verify(result);
        }

        [Fact]
        public void ExampleInvoiceWithTaxes()
        {
            var elfCompanies = LoadElfCompanies();
            var invoice = LoadInvoice(ResourcesOrderWithTaxes);

            var result = CreateInvoicePrinter()
                .PrintWithTaxes(invoice, elfCompanies);

            Approvals.Verify(result);
        }

        private static Dictionary<string, ElfCompany> LoadElfCompanies()
        {
            var json = File.ReadAllText("Resources/elfCompanies.json");
            var data = JsonConvert.DeserializeObject<Dictionary<string, JObject>>(json);
            var companies = new Dictionary<string, ElfCompany>();

            foreach (var kvp in data)
            {
                companies[kvp.Key] = new ElfCompany(
                    kvp.Value["name"].ToString(),
                    kvp.Value["type"].ToString(),
                    kvp.Value["region"].ToString()
                );
            }
            return companies;
        }

        private static Invoice LoadInvoice(string resourceFileName)
        {
            var json = File.ReadAllText(resourceFileName);
            var data = JObject.Parse(json);
            var customer = data["customer"].ToString();
            var deliveries = data["deliveries"]
                .Select(d => 
                    new Delivery(d["companyID"].ToString(), d["packages"].ToObject<int>()))
                .ToList();

            return new Invoice(customer, deliveries);
        }
    }
}
