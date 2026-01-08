import { InvoicePrinter } from '../src/InvoicePrinter';
import { Invoice } from '../src/Invoice';
import { ElfCompany } from '../src/ElfCompany';
import * as fs from 'fs';
import { verifyAsJSON } from 'approvals';

describe('InvoicePrinter', () => {
  test('exampleInvoice', () => {
    const elfCompanies = loadElfCompanies();
    const invoice = loadInvoice();
    const printer = new InvoicePrinter();

    const result = printer.print(invoice, elfCompanies);

    verifyAsJSON(__dirname, result);
  });

  // TODO: Add exampleInvoiceWithTaxes test here

  function loadElfCompanies(): Map<string, ElfCompany> {
    const json = fs.readFileSync('test/resources/elfCompanies.json', 'utf8');
    const data = JSON.parse(json);
    const companies = new Map<string, ElfCompany>();

    for (const [key, value] of Object.entries(data)) {
      const company = value as any;
      companies.set(key, {
        name: company.name,
        type: company.type,
        region: company.region
      });
    }
    return companies;
  }

  function loadInvoice(): Invoice {
    const json = fs.readFileSync('test/resources/order.json', 'utf8');
    return JSON.parse(json);
  }
});
