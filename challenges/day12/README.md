# Day 12 - Human password policy

> _December 12, 2025 - Human password policy

![snippet of the day](img/day12.png)

  ==> **Loading Fractured Memory** <==

On the twelth day of the journey, you are the help the elves open their Christmas market.

This year, it's a special occasion, they are doing it with humans for the very first time.

... but they don't trust them.

In order to access the building, human market sellers must enter their password and the verification system is not ready.
Indeed, the elves have already a password policy in place but not the humans.

Can you help them? (Detailed requirements below)

---

## Challenge — Implement a human password policy.

Today, you start right after the implementation of the basic elven password policy.

The idea is to think how you will implement another policy without breaking the other one.

 💡HINT💡: You start the day in the pre-refactoring phase!

> Focus: pure TDD loop — RED / GREEN / REFACTOR.

---

## Requirements

### 1. Elf password policy (loose) — **what is already implemented**

An **elf** password is valid if:

- It has **at least 6 characters**
- It contains **at least one uppercase letter**
- It contains **exactly one digit**

Everything else is allowed (for this exercise we don’t care about symbols or invalid characters yet).

Examples that **should be valid**:

- `Abcde1`
- `ELfMAr1`

Examples that **should be invalid**:

- `""` (empty) → too short
- `"Abc1"` → too short
- `"abcdef"` → no uppercase, no digit
- `"abcde1"` → no uppercase
- `"ABCDEF"` → no digit
- `"Abcde12"` → more than one digit

The provided tests document this behaviour and should all pass with the initial implementation.

---

### 2. Human password policy (strict) — **what you’ll implement**

Humans must use a **stricter** password policy.

A **human** password is valid if:

- It has **at least 8 characters**
- It contains **at least one uppercase letter**
- It contains **at least one lowercase letter**
- It contains **at least one digit**
- It contains **at least one special character** from this whitelist:
  - `.`, `*`, `#`, `@`, `$`, `%`, `&`
- It contains **no invalid characters**:
  - only letters, digits, and the special characters listed above are allowed.

Examples that should be valid:

- `P@ssw0rd`
- `Advent0fCraft&`

Examples that should be invalid:

- Too short: `"xxxxxxx"`
- No capital letter: `"adventofcraft"`, `"p@ssw0rd"`
- No lowercase letter: `"ADVENTOFCRAFT"`, `"P@SSW0RD"`
- No number: `"Adventofcraft"`, `"P@sswOrd"`
- No special character: `"Adventof09craft"`, `"PAssw0rd"`
- Invalid character: `"Advent@of9Craft¨"`, `"P@ssw^rd"`

(These examples are taken from the original strict version you’ll recreate.)

---

## Where to play

For the exploration of the fractured memories, since they are not your own memories
We will go to /exercises and the dayNN

You can then pick the stack you want to use as your **main stack**.

- `exercises/2024/day12/<your-stack>/`

This is your playground.

---

## Share with the community

On Discord, you can share:

- How did starting from a **simple** elf-only implementation influence your design?
- Which test doubles did you end up creating (fake, stub, spy, …)?
- Did the design of your production code become easier or harder to work with?

If you want your work to be public, add your file to:

- `community/solutions/dayNN/` by copying the template:  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- then open a pull request.

In `journey/your-name.md`, write a short entry for today:

 - When adding the human policy:
   - Which tests did you write first?
   - Did you reuse ideas / patterns from the elf tests?
 - At which point did duplication become painful enough to justify refactoring?

**Let's make it the best market experience. 🎅**
