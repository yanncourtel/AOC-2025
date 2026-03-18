# Implementation Plan: Day 16 – Code as Documentation

## Context

The International Xmas Emergency Council has a 1987-era scheduling system with no documentation.
The code works but nobody understands it — incidents in Tokyo and Mumbai have resulted.
The challenge is NOT to rewrite the code; it is to **restore the lost knowledge** by making
the logic visible through tests (tests as documentation).

The arrival logic in `Program.cs` is a single ternary expression with two conditions:
- `tz < -5` → day offset (Dec 24 vs 25)
- `tz < 0`  → time slot (23:00 vs 20:00)

The goal: extract that logic into a testable class, then write expressive tests that
answer all 5 investigation tickets and serve as human-readable documentation.

---

## The Logic (pre-analysed)

Three distinct behaviours exist, driven by two boundary conditions:

| Zone | Condition | Day | Time |
|------|-----------|-----|------|
| Far-west (UTC-6 to UTC-12) | `tz < -5` | Dec 25 | 23:00 |
| Americas (UTC-5 to UTC-0-) | `-5 ≤ tz < 0` | Dec 24 | 23:00 |
| Europe/Asia/Pacific (UTC+0+) | `tz ≥ 0` | Dec 24 | 20:00 |

Ticket answers:
- **TICKET-101**: UTC-10 (Hawaii) hits `tz < -5` → Dec **25**; UTC-5 (New York) does not → Dec **24**
- **TICKET-102**: UTC+0 (London) → **20:00**; UTC-5 (New York) → **23:00** — fixed slots, not offset-compensated
- **TICKET-103**: `-5` is NOT `< -5` → grouped with Americas (Dec 24/23:00); `0` is NOT `< 0` → grouped with Europe (20:00)
- **TICKET-104**: `-3.5` (Newfoundland) → 23:00 Dec 24; `+5.5` (Mumbai) → 20:00 Dec 24 — doubles handle fractional offsets natively
- **TICKET-105**: Exactly 3 distinct behaviours

---

## Implementation Steps

### Step 1 – Create `SantaScheduling/SantaScheduler.cs`

Extract the arrival `DateTime` construction into a dedicated static class:

```csharp
namespace SantaScheduling;

public static class SantaScheduler
{
    public static DateTime GetArrivalTime(double timezoneOffset) =>
        new DateTime(
            2024, 12,
            24 + (timezoneOffset < -5 ? 1 : 0),
            timezoneOffset < 0 ? 23 : 20,
            0, 0);
}
```

### Step 2 – Update `Program.cs` (minimal)

In the `if (cmd == "a")` block, replace the inline `new DateTime(...)` with:
```csharp
DateTime arrival = SantaScheduler.GetArrivalTime(tz);
```
The departure block remains untouched (Klaus's warning applies).

### Step 3 – Iterative ticket-by-ticket test writing (TDD-style)

Each micro-step follows this rhythm:
1. Write one ticket's test with real assertions
2. `dotnet test` → confirm green
3. **Manually break the logic** → confirm the test turns red
4. Restore; optionally do a small refactor
5. Move to the next ticket

---

**Micro-step 3a — TICKET-101** (day boundary: Hawaii vs New York)
```
[Theory]
Hawaii  (-10) → Dec 25   — tz < -5 is true
NewYork ( -5) → Dec 24   — boundary belongs to this group
```
Break hint: change `tz < -5` to `tz < -4` → New York flips to Dec 25.

---

**Micro-step 3b — TICKET-102** (time slot: London vs New York)
```
[Theory]
London  ( 0) → 20:00   — tz >= 0
NewYork (-5) → 23:00   — tz < 0
```
Break hint: change `23` to `22` → New York time assertion fails.

---

**Micro-step 3c — TICKET-103** (exact boundary values)
```
[Fact]  tz = -5 exactly → Dec 24, 23:00   (Americas group)
[Fact]  tz =  0 exactly → Dec 24, 20:00   (Europe/positive group)
```
Break hint: change `< -5` to `<= -5` → UTC-5 flips to Dec 25.

---

**Micro-step 3d — TICKET-104** (fractional/half-hour offsets)
```
[Theory]
Newfoundland (-3.5) → Dec 24, 23:00
Mumbai       (+5.5) → Dec 24, 20:00
```
Break hint: cast `timezoneOffset` to `(int)` inside the method → half-offsets truncate incorrectly.

---

**Micro-step 3e — TICKET-105** (complete zone map as living documentation)
```
[Theory]
Zone A (tz < -5):    -12, -10, -5.1  → Dec 25, 23:00
Zone B (-5 ≤ tz<0):  -5, -3.5, -0.5 → Dec 24, 23:00
Zone C (tz ≥ 0):     0,   5.5,  14  → Dec 24, 20:00
```

---

## Critical Files

| File | Action |
|------|--------|
| `SantaScheduling/SantaScheduler.cs` | **Create** — extracted static method |
| `SantaScheduling/Program.cs` | Minimal edit: delegate arrival to `SantaScheduler.GetArrivalTime(tz)` |
| `SantaScheduling.Tests/SantaSchedulingTests.cs` | Replace all 5 stubs with real assertions |

---

## Verification

```bash
dotnet build SantaScheduling.sln
dotnet test

dotnet run --project SantaScheduling -- a -10   # → 25/12/2024 23:00:00
dotnet run --project SantaScheduling -- a -5    # → 24/12/2024 23:00:00
dotnet run --project SantaScheduling -- a 0     # → 24/12/2024 20:00:00
dotnet run --project SantaScheduling -- a 5.5   # → 24/12/2024 20:00:00
```
