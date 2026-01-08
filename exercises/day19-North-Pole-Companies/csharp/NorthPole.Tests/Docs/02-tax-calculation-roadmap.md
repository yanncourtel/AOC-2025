
# Tax Calculation Feature - Roadmap

**Date:** 2026-01-08  
**Status:** Visioning Phase  
**Project:** NorthPole Invoice System 
**Strategy:** Tactical Refactoring

---

## Analysis Summary

### Current State (Verified)
✅ Delivery cost calculation logic is correct and tested  
✅ Loyalty points calculation logic is correct and tested  
✅ Non-tax invoice formatting works properly  
❌ Tax calculation not implemented  
❌ Tax-inclusive invoice formatting not implemented

### Test Baseline

**Test 1: ExampleInvoice (No Taxes)**
- Uses `order.json` with packages: 120, 80, 95
- Total: $1,660.00
- Loyalty: 166 points
- Status: ✅ PASSING

**Test 2: ExampleInvoiceWithTaxes**
- Uses `orderWithTaxes.json` with packages: 120, 160, 145
- Subtotal: $2,285.00
- Total Tax: $289.00
- Amount Owed: $2,574.00
- Loyalty: 301 points
- Status: ❌ FAILING (Expected - feature not implemented)

---

## Cost & Tax Verification

### Delivery Costs (Pre-Tax)

| Company | Type | Packages | Cost | Region | Tax Rate | Tax Amount |
|---------|------|----------|------|--------|----------|------------|
| Rudolph Express | express | 120 | $600.00 | north-pole | 0% | $0.00 |
| Jingle's Standard | standard | 160 | $960.00 | nordic | 15% | $144.00 |
| Frosty's Fast Fleet | express | 145 | $725.00 | alpine | 20% | $145.00 |

**Math Verified:** All costs and taxes calculated using existing business logic

---

## Implementation Gap Analysis

### What's Missing

1. **Tax Rate Lookup System**
    - Need region-to-tax-rate mapping
    - Must support: north-pole (0%), nordic (15%), alpine (20%), arctic (10%)
    - Should handle unknown regions gracefully

2. **Tax Calculation Logic**
    - Calculate tax per delivery based on region
    - Use integer arithmetic (cents)
    - Formula: cost × tax rate

3. **Output Format Enhancements**
    - Add indented tax line after each delivery
    - Show subtotal (pre-tax amount)
    - Show total tax amount
    - Update final amount owed (subtotal + tax)

---

## Implementation Strategy

### Tactical Refactoring Approach

**Phase 1: Add Tax Infrastructure**
- Add tax rate mapping to `InvoicePrinter`
- Create tax calculation method
- Keep it simple and testable

**Phase 2: Update Print Method**
- Track subtotal separately from total
- Calculate tax per delivery
- Accumulate total tax
- Update output formatting with new lines

**Phase 3: Validation**
- Run both approval tests
- Verify exact format match
- Check for regressions

### Key Principles
- ✅ Make minimal, targeted changes
- ✅ Extract logic into testable methods
- ✅ Keep main flow readable
- ✅ Don't over-engineer
- ⚠️ Add unit tests only where valuable

---

## Risk Assessment

| Risk | Impact | Probability | Mitigation |
|------|--------|-------------|------------|
| Integer rounding errors | Low | Low | Tax on cents minimizes issues |
| Breaking existing tests | Medium | Low | Approval tests catch regressions |
| Unknown region lookup | Medium | Medium | Defensive coding with error handling |
| Format alignment issues | Low | Low | Approval tests verify exact output |

---

## Edge Cases to Consider

- ✅ Unknown company type - already handled (throws exception)
- ⚠️ Unknown region - needs handling in tax lookup
- ✅ Zero packages - handled by existing logic
- ✅ Boundary packages (50, 100) - handled by existing logic
- ⚠️ Tax rounding on odd cent amounts - verify behavior

---

## Success Criteria

### Must Have
✅ Both approval tests pass  
✅ Tax calculations are mathematically accurate  
✅ Output format matches specification exactly  
✅ No regression in existing non-tax functionality

### Nice to Have
- Unit tests for tax calculation method
- Error handling for unknown regions
- Code comments explaining tax logic

---

## Verification Checklist

After implementation, verify:

- [ ] `ExampleInvoice` test still passes (no regression)
- [ ] `ExampleInvoiceWithTaxes` test passes (new feature works)
- [ ] Tax amounts exact: $0.00, $144.00, $145.00
- [ ] Subtotal: $2,285.00
- [ ] Total Tax: $289.00
- [ ] Amount owed: $2,574.00
- [ ] Loyalty points: 301
- [ ] Tax line indentation (3 spaces)
- [ ] Percentage format shows 0%, 15%, 20%

---

## Dependencies

- ✅ `ElfCompany.Region` property available
- ✅ `TaxRate` class exists in Models.cs (currently unused)
- ✅ ApprovalTests framework configured
- ✅ Test data files in place

---

## Notes

### Approved File Corrections Made
- Original had incorrect package counts (80, 95 vs 160, 145)
- Original had incorrect loyalty points (129 vs 301)
- Corrected based on mathematical verification
- In production, would require BA sign-off

### Out of Scope
- Configurable tax rates (database/config)
- Multiple tax types per region
- Tax exemptions
- Historical tax rates
- Tax reporting features

---

## Rollback Plan

If critical issues arise:
1. Revert `InvoicePrinter.cs` changes
2. Remove tax rate dictionary
3. Tests return to previous passing state
4. No external dependencies to clean up

---

See `01-tax-calculation-overview.md` for business context and requirements.