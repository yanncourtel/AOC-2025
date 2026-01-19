# Tax Calculation Feature - Refactoring Map

**Date:** 2026-01-08  
**Status:** Implementation Complete - Refactoring Phase  
**Current State:** Both tests passing, code duplication present

---

## Current Domain Model

### Existing Classes (Models.cs)

**Invoice**
- Customer: string
- Deliveries: List<Delivery>

**Delivery**
- CompanyID: string
- Packages: int

**ElfCompany**
- Name: string
- Type: string (express/standard)
- Region: string

**TaxRate (unused)**
- Name: string
- TaxRateValue: double
- Description: string

---

### Current Responsibilities in InvoicePrinter

| Responsibility | Location | Domain |
|----------------|----------|---------|
| Delivery cost calculation | CalculateDeliveryCost() | Pricing |
| Loyalty points calculation | CalculateLoyaltyPoints() | Loyalty Program |
| Tax calculation | CalculateTax() | Tax Policy |
| Tax rate storage | TaxRates dictionary | Tax Policy |
| Invoice aggregation | Both Print methods | Invoice Calculation |
| Text formatting | Both Print methods | Presentation |
| Currency formatting | Inline in Print methods | Presentation |

**Problem:** 7 distinct responsibilities in one class (SRP violation)

---

## Identified Code Smells

### 1. Duplication (High Priority)
- Approximately 80% duplication between Print() and PrintWithTaxes()
- Similar loops with different outputs
- Repeated currency formatting pattern

### 2. Mixed Abstraction Levels (Medium)
- Business logic mixed with presentation
- Low-level string building alongside high-level orchestration

### 3. Primitive Obsession (Medium)
- Money represented as int (cents)
- Tax information scattered across multiple primitives
- No domain objects for calculated results

### 4. Long Methods (Low)
- Print methods do too much
- Each method: calculate + accumulate + format

### 5. Feature Envy (Low)
- CalculateDeliveryCost knows internal details of ElfCompany.Type
- Switch statement on company type should be polymorphic

### 6. Data Clumps (Low)
- Cost, tax, and taxInfo always travel together
- Subtotal, totalTax, and loyaltyPoints always returned together

---

## Missing Domain Concepts

### 1. Money Value Object
**Current:** int (cents) scattered everywhere  
**Problem:** No encapsulation, repeated conversion logic  
**Solution:** Money value object with AmountInCents and FormatAsCurrency()

### 2. Tax Value Object
**Current:** Tax data scattered (region name, rate, amount)  
**Problem:** No cohesion, repeated lookups  
**Solution:** Tax value object encapsulating region, rate, and amount

### 3. InvoiceLine Entity
**Current:** Calculated inline during printing  
**Problem:** No representation of calculated line item  
**Solution:** InvoiceLine with CompanyName, Packages, Cost, Tax

### 4. CalculatedInvoice Aggregate
**Current:** Results scattered as local variables  
**Problem:** No first-class concept of calculated invoice  
**Solution:** CalculatedInvoice aggregate with all calculated data

### 5. Pricing Strategy Pattern
**Current:** Switch statement on company type  
**Problem:** Hard to extend, violates Open/Closed  
**Solution:** IPricingStrategy with ExpressPricing and StandardPricing

---

## Refactoring Roadmap

### Phase 1: Extract Helpers (Low Risk)

**Goal:** Reduce duplication without changing structure

**Steps:**
- [x] Extract FormatMoney(int amountInCents) method
- [x] Extract CultureInfo as readonly field
- [x] Extract GetTaxInfo(string region) method

**Impact:** Minor duplication reduction, improved readability  
**Risk:** Very low  
**Tests:** Should pass without changes

---

### Phase 1.5: Domain Model Coherence (Low-Medium Risk)

**Goal:** Remove manual relationship resolution, create cohesive domain objects

**Steps:**
- [x] Create EnrichedDelivery (or DeliveryLine) class
- [x] Enrich deliveries at method entry point
- [x] Update loops to use enriched model
- [x] Remove dictionary parameter passing into calculation methods
- [x] Creating EnrichedInvoice object containing the EnrichedDeliveries
- [x] Update Print methods to use EnrichedInvoice

**Impact:** Cleaner domain model, simpler method signatures  
**Risk:** Medium (changes method internals, but tests protect)  
**Tests:** Should pass (behavior unchanged)

---

### Phase 2: Introduce Value Objects (Medium Risk)

**Goal:** Create domain vocabulary

#### Step 2.1: Money Value Object
- [x] Create Money struct with AmountInCents property
- [x] Add FormatAsCurrency(CultureInfo) method
- [x] Add arithmetic operators (+, -, *, /)
- [x] Update CalculateDeliveryCost return type
- [x] Update CalculateTax return type
- [x] Update all local variables and accumulation logic

**Impact:** Better domain modeling, type safety  
**Risk:** Medium (many changes, compiler helps)  
**Tests:** Should pass (wrapping/unwrapping values)

#### Step 2.2: Tax Value Object
- [x] Create Tax class with RegionName, Rate, Amount properties
- [x] Add FormatDescription() method
- [x] Update CalculateTax to return Tax instead of int
- [x] Simplify tax line formatting

**Impact:** Tax information cohesive and testable  
**Risk:** Low  
**Tests:** Should pass

---

### Phase 3: Introduce Domain Entities (Medium Risk)

**Goal:** Model the invoice calculation result

#### Step 3.1: InvoiceLine
- [x] Create InvoiceLine class
- [x] Add properties: CompanyName, Packages, Cost, Tax (nullable)
- [x] Extract CalculateInvoiceLine(Delivery, ElfCompany, bool) method
- [x] Change loop to build List of InvoiceLine

**Impact:** Calculation separated from formatting  
**Risk:** Medium  
**Tests:** Should pass

#### Step 3.2: CalculatedInvoice
- [x] Create CalculatedInvoice class
- [x] Add properties: Customer, Lines, Subtotal, TotalTax, TotalAmount, LoyaltyPoints
- [x] Extract CalculateInvoice method returning CalculatedInvoice
- [x] Update Print methods to format CalculatedInvoice

**Impact:** Full separation of calculation and presentation  
**Risk:** Medium-High  
**Tests:** Should pass

---

### Phase 4: Extract Formatters (Low Risk)

**Goal:** Separate presentation from business logic

**Steps:**
- [ ] Create IInvoiceFormatter interface
- [ ] Implement TextInvoiceFormatter class
- [ ] Move all StringBuilder logic to formatter
- [ ] Inject formatter into InvoicePrinter
- [ ] Print methods become: Calculate then Format

**Impact:** Complete separation of concerns  
**Risk:** Low (calculation already separated)  
**Tests:** Should pass

---

### Phase 5: Extract Calculators (Optional)

**Goal:** Full SOLID compliance

#### Step 5.1: Extract Pricing
- [ ] Create IDeliveryPricer interface
- [ ] Implement DeliveryPricer class
- [ ] Move CalculateDeliveryCost logic
- [ ] Consider Strategy pattern for express/standard

#### Step 5.2: Extract Tax Calculation
- [ ] Create ITaxCalculator interface
- [ ] Implement TaxCalculator class
- [ ] Move CalculateTax and TaxRates
- [ ] Consider using existing TaxRate model

#### Step 5.3: Extract Loyalty Calculation
- [ ] Create ILoyaltyCalculator interface
- [ ] Implement LoyaltyCalculator class
- [ ] Move CalculateLoyaltyPoints logic

#### Step 5.4: Create Domain Service
- [ ] Create InvoiceCalculator service
- [ ] Inject IDeliveryPricer, ITaxCalculator, ILoyaltyCalculator
- [ ] Move calculation orchestration
- [ ] InvoicePrinter becomes thin facade

**Impact:** Fully testable components, easy to extend  
**Risk:** Low (if Phase 3 done correctly)  
**Tests:** Should pass, add unit tests per calculator

---

## Success Criteria Per Phase

### Phase 1
- [x] FormatMoney helper exists
- [x] Used in both Print methods
- [x] Tests pass
- 
### Phase 1.5
- [x] Using EnrichedDelivery instead of Delivery
- [x] Using EnrichedInvoice instead of Invoice

### Phase 2
- [ ] Money type exists and is used everywhere
- [ ] Tax type exists and encapsulates tax info
- [ ] No primitive ints for money amounts
- [ ] Tests pass

### Phase 3
- [ ] InvoiceLine and CalculatedInvoice exist
- [ ] Calculation method returns CalculatedInvoice
- [ ] Print methods only format, do not calculate
- [ ] Tests pass

### Phase 4
- [ ] IInvoiceFormatter exists
- [ ] InvoicePrinter delegates to formatter
- [ ] No StringBuilder in InvoicePrinter
- [ ] Tests pass

### Phase 5
- [ ] All calculators extracted to separate classes
- [ ] Each calculator has interface
- [ ] InvoicePrinter is thin facade
- [ ] Unit tests for each calculator
- [ ] Integration tests pass

---

## Rollback Strategy Per Phase

| Phase | Rollback Action | Risk Level |
|-------|-----------------|------------|
| Phase 1 | Inline FormatMoney calls | Very Low |
| Phase 2 | Revert Money/Tax types, use int | Low |
| Phase 3 | Revert to inline calculation | Medium |
| Phase 4 | Move formatting back to Print | Low |
| Phase 5 | Merge calculators back | Low |

---

## Testing Strategy

### Current Coverage
- Approval tests for both scenarios (passing)
- No unit tests for calculations

### Recommended Testing Per Phase

**Phase 1:** No new tests needed

**Phase 2:** Optional unit tests
- Money.FormatAsCurrency() tests
- Tax.FormatDescription() tests

**Phase 3:** Recommended unit tests
- CalculateInvoiceLine() tests
- CalculatedInvoice validation tests

**Phase 4:** No new tests needed (approval tests sufficient)

**Phase 5:** Essential unit tests
- DeliveryPricer tests (express/standard scenarios)
- TaxCalculator tests (all regions)
- LoyaltyCalculator tests (express/standard, boundaries)

---

## Current Status

- Implementation Complete: Both tests passing
- Domain Analysis Complete: Responsibilities identified
- Refactoring Phase: Ready to start Phase 1

**Next Action:** Extract FormatMoney helper (Phase 1, Step 1)

---

## Notes

- **Priority:** Focus on Phases 1-3 for tactical improvement
- **Phases 4-5:** Only if time permits or planning future features
- **Test Coverage:** Keep approval tests passing after each step
- **Commit Frequency:** After each successful step (micro-commits)
- **Collaboration:** Consider pairing for Phase 3+ (higher risk)

---

## References

- 01-tax-calculation-overview.md - Business context and requirements
- 02-tax-calculation-roadmap.md - Implementation analysis and verification