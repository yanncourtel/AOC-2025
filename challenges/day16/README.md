# Day 16 - Code as a documentation

> _December 16, 2025 - Code as a documentation

![snippet of the day](img/day16.png)

  ==> **Loading Fractured Memory** <==

On the 16th day, your fractured memory is bringing you right into the International Xmas Emergency Council!

The room is tense. Representatives from 12 countries are present, and they're not happy.

**"Tokyo got Santa at the wrong time again!"**  
**"Mumbai's logistics were completely off!"**  
**"We can't keep running Christmas on code nobody understands!"**

The system was written in 1987 and there is no documentation. The code works—mostly—but every few years there's an "incident." Last year it was Mumbai. The year before, Tokyo. Small timing errors that cause massive logistical headaches.

**The code isn't broken. The code is *terrifying*.**

## Challenge — Restore the lost knowledge.

Today, your first mission is not to refactor a code.
They don't need you to rewrite the code (not yet, anyway). 

They just need to **understand what it does**.

The team left you a backlog filled with tickets to document.
**You need to document them all**

Remember the council does not read code, they need a real documentation. (use a type of visual documentation)

**Hint:** Think what is the shortest feedback loop? Is it to run the program?

---

## The Ticket Backlog

Your team has collected questions from various incidents. Use these as starting points:

### 🎫 Investigation Tickets

**TICKET-101: Why does Hawaii get December 25th but New York gets December 24th?**
- Hawaii: UTC-10
- New York: UTC-5
- They both get 11 PM... but different days? What's the pattern?

**TICKET-102: Why does London get 8 PM but New York gets 11 PM?**
- London: UTC+0, gets 8 PM on Dec 24th
- New York: UTC-5, gets 11 PM on Dec 24th
- Both Christmas Eve, but 3 hours apart. Why?

**TICKET-103: What happens at exactly UTC-5 and UTC+0?**
- These seem to be boundary points. How are they treated?
- Are they grouped with the zones before or after them?

**TICKET-104: How does the system handle half-hour timezones?**
- Mumbai: UTC+5.5
- Newfoundland: UTC-3.5
- The 2023 incident happened with one of these...

**TICKET-105: What's the complete rule set?**
- Can you document all the business rules for timezones from -12 to +14?
- How many distinct behaviors are there?

---

## Where to play

For the exploration of the fractured memories, since they are not your own memories
We will go to /exercises and the dayNN

You can then pick the stack you want to use as your **main stack**.

- `exercises/day16/<your-stack>/`

This is your playground.

---

## Share with the community

On Discord, you can share:

 - What did you decide to do to understand the code?
 - Did you switch your approach midway through your refactoring?

If you want your work to be public, add your file to:

- `community/solutions/dayNN/` by copying the template:  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- then open a pull request.

In `journey/your-name.md`, write a short entry for today:

 - Is Test As A Documentation an automatic approach for you?
 - How did you feel doing pre-refactoring on existing legacy code?

**Good luck restoring the lost knowledge! 📜**
