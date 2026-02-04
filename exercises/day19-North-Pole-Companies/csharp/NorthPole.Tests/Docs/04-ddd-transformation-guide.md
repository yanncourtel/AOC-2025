# NorthPole API DDD Transformation Guide

## Objective
Transform the current invoice API toward clearer Domain-Driven Design boundaries while preserving behavior.

## Current Domain Snapshot
- Core concepts: `Invoice`, `Delivery`, `ElfCompany`, `Money`, `Tax`, `Region`.
- Main flow: `Invoice` -> enrichment with company catalog -> calculation -> text formatting.
- Current technical split: `Domain/`, `Calculation/`, `Formatting/`, `InvoicePrinter` orchestration.

## DDD Target State
- Rich domain model with explicit invariants in aggregate/entity constructors and methods.
- Value objects for key primitives (customer identity/name, company id/type, package count).
- No nullable domain concepts for tax; use explicit zero/no-tax value.
- Behavior moved from extension helpers into domain types where appropriate.
- Clear distinction between domain model and read/presentation models.

## Identified Gaps (From Review)
1. Invariants are not consistently enforced (`Invoice`, `Delivery` accept invalid data; mutable collections exposed).
2. Null is used as a domain concept (`Tax?` / `Tax.NoTax => null`).
3. Primitive obsession (`string`/`int` for identity/type/count).
4. Domain behavior is scattered in extension methods and service orchestration.
5. Money multiplies by `double`, risking rounding ambiguity.
6. Parsing concerns (e.g., `Region` string parsing) appear in domain layer.

## Transformation Principles
- Make invalid states unrepresentable.
- Preserve behavior first, then improve model expressiveness.
- Keep refactors incremental and verifiable.
- Limit each step to one concern and small diffs.
- Keep framework/infrastructure concerns outside domain.

## Incremental Refactor Plan

### Phase 0 - Safety Net
- Add characterization tests around current invoice outputs (with and without tax).
- Add focused tests for pricing, loyalty, and tax edge cases.

### Phase 1 - Invariants and Encapsulation
1. Add constructor guards:
   - `Invoice`: non-empty customer, non-null deliveries.
   - `Delivery`: non-empty company id, package count >= 0.
2. Replace mutable `List<T>` exposure with read-only views (`IReadOnlyList<T>`).
3. Ensure aggregate root (`Invoice`) controls any collection mutation.

### Phase 2 - Tactical DDD Patterns
1. Introduce value objects:
   - `CustomerName`
   - `CompanyId`
   - `CompanyType`
   - `PackageCount`
2. Replace nullable tax with explicit domain value:
   - `Tax.Zero` (rate 0, amount 0)
   - `InvoiceLine.Tax` non-nullable
3. Move behavior onto domain model:
   - Move `EnrichWith`/`HasDeliveries` into `Invoice` methods.

### Phase 3 - Boundary Clarity
1. Clarify application/read model role of `CalculatedInvoice` (e.g., `InvoiceSummary`).
2. Keep formatting concerns in presentation layer only.
3. Move string-to-domain parsing to input mapping layer (anti-corruption boundary).

### Phase 4 - Precision and Policy Hardening
1. Update money operations to use `decimal` and explicit rounding rules.
2. Centralize tax/pricing policy decisions and name them in domain language.
3. Replace string-keyed pricer lookup with typed strategy selection (`CompanyType`).

## Proposed Domain Structure (Target)
- `NorthPole.Domain`
  - Aggregates: `Invoice`
  - Entities: `Delivery`, `ElfCompany`
  - Value Objects: `Money`, `Tax`, `CustomerName`, `CompanyId`, `CompanyType`, `PackageCount`, `Region`
  - Domain Services: pricing/tax policies only where behavior spans entities/VOs
- `NorthPole.Application`
  - Use cases/orchestration
  - Read models (`InvoiceSummary`)
- `NorthPole.Infrastructure`
  - External mappings, repositories/catalog adapters
- `NorthPole.Presentation`
  - Formatters / API contracts

## Definition of Done per Step
For each refactor step:
- Builds successfully.
- Existing behavior tests remain green.
- New invariants are covered with negative tests.
- Public API changes are documented (if any).

## Risk Register
- Medium: introducing value objects may ripple through many signatures.
- Medium: changing tax nullability affects formatting and calculation paths.
- Low: guard clauses and read-only collection exposure.

## Suggested Execution Order
1. Safety tests
2. Invariants + encapsulation
3. Tax nullability removal
4. Value objects
5. Behavior migration from extensions
6. Boundary separation and naming cleanup
7. Money precision hardening

## Open Decisions
- Is `ElfCompany` inside the same bounded context or a reference context?
- Should company type taxonomy be closed (`enum`) or open/extensible?
- Is tax strictly region-based for all future scenarios?

## Tracking Checklist
- [ ] Baseline behavior tests added
- [ ] Invariants enforced in domain constructors
- [ ] Domain collections encapsulated
- [ ] `Tax.Zero` introduced and null tax removed
- [ ] Primitive fields replaced by value objects
- [ ] Domain behavior moved into aggregate/entity methods
- [ ] Domain/application/presentation boundaries clarified
- [ ] Money precision policy finalized and tested
