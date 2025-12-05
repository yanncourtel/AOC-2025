# Day 05 - Battle of TDD, Human vs AI

![snippet of the day](img/day05.png)

> **Memory fragment – 2024 · Day 5**  
> I remember these Elf IDs.  
> Lines of digits, rules scribbled on a page,  
> and a growing list of tests in the margin.  
> I thought I had covered everything…  
> until someone (or something) asked:  
> “What about *this* case?”  
> ...  
> *Maybe I wasn’t the only one writing tests that day.*

On the fifth day of the journey, we revisit the [memory](https://github.com/advent-of-craft/2024/blob/main/docs/exercise/day05/challenge.md) of the **EID validator** from the 2024 edition.

Last time, the focus was to design the validator with TDD.

This time, the twist is different:

> You and an **AI assistant** will both propose a test list  
> and you’ll decide which one you trust.

You can also combine both.

---

## Challenge — Use the human and AI brains to implement the validator

Today’s challenge has four phases:

1. **Understand the business rules again**
2. **Write your own test list (Human)**
3. **Ask an AI for a test list (AI)**
4. **Compare and/or adjust, and implement with TDD**

You can do the whole exercise or the easy more by dismissing the control key part.  
A focused subset driven by good tests is enough.

---

## Recall the EID rules

From the 2024 memory, an EID (Elf Identifier) has 8 digits:

| Positions | Meaning        | Possible values                                   |
|----------|----------------|---------------------------------------------------|
| 1        | Sex            | `1` Sloubi, `2` Gagna, `3` Catact                 |
| 2–3      | Year of birth  | last two digits, from `00` to `99`                |
| 4–6      | Serial number  | birth order, from `001` to `999`                  |
| 7–8      | Control key    | `97 - (first 6 digits mod 97)`, from `01` to `97` |

The goal: design a validator (or validation core) that enforces these rules.

---

## Remembering the TDD flow

Remember the TDD flow:
  1) write the test first or modify one → it **fails** (red),
  2) write the simplest code to make it pass → **green**,
  3) refactor while staying green

A tip: let the execution of the tests guide you to where you need to go.

Let the best ideas win, not the loudest brain.

---

## Where to play

Pick the stack you want to use as your **main stack** this year and open the Day 5 memory from 2024:

- `memories/2024/exercises/<your-stack>/day05`

This is your playground for the EID validator.

If you feel short on time, you can do the easy mode with:

- limit yourself to **format + sex + serial** first,
- add control key tests only if you have enough runway.

Let your tests lead the way ➰💫

---

## Share with the community

Post your findings on Discord and get feedback from the community.  
Share your test list and explain your decision to merge / adjust or discard the list.

If you want your work to be public, add your file to:

- `community/solutions/dayNN/` by copying the template:  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- and open a pull request.

In `journey/your-name.md`, write a short entry for today:

- What did my **test list** look like before AI?  
- What did AI help me or surprised me?  
- Which tests did I end up keeping, and why?  
- Did starting from a curated test list change how TDD felt?

It’s not about who wins but how the experience challenges your thinking. 🧠🤖
