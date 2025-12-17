# Day 18 - Risky Sleigh

> _December 18, 2025 - Risky Sleigh_

![snippet of the day](img/day18.png)

  ==> **Loading Fractured Memory** <==

On the 18th day, your fractured memory is throwing you in a so-called perfect world! 
A world in which bad code have become illegal.

A month ago, the World Government has passed the **Code Safety Act**

All software systems must eliminate prohibited risk patterns by December 25th or face **mandatory shutdown**.

Santa's sleigh control system has been flagged for **multiple violations**. You will have to refactor enough risks to pass compliance and keep Christmas on track!

## Challenge — Make the Sleigh system risk compliant.

Today, the exercise is asking you to assess the risks in a system and drive your refactoring from it.

A good portion of your time should be to analyze the code base and understand which risk there is and how critical they are.
It's not so much about changing the code than it is to document a comprehensive refactoring roadmap.

**Hint:** AI is a good starting point to assess the risks and help you prioritize them.

---

## 📊 Government Risk Registry

| Risk ID | Description | CP Value | Difficulty |
|---------|-------------|----------|------------|
| **R1** | Mutable public state fields (status, action) | 5 | 🟢 Easy |
| **R2** | Direct manipulation of dependency internals | 3 | 🟢 Easy |
| **R3** | No state transition validation | 4 | 🟡 Medium |
| **R4** | Magic numbers without explanation | 2 | 🟢 Easy |
| **R5** | Exception handling leaves uncertain state | 4 | 🟡 Medium |
| **R6** | Missing lifecycle sequence enforcement | 3 | 🟡 Medium |
| **R7** | No operation audit trail | 2 | 🟢 Easy |

**Total Audited:** 23 CP  
**Required to Pass:** 10 CP

Be aware that 10 CP is a minimum and that more could be required.

---

## Where to play

For the exploration of the fractured memories, since they are not your own memories
We will go to /exercises and the dayNN

You can then pick the stack you want to use as your **main stack**.

- `exercises/day18/<your-stack>/`

This is your playground.

---

## Share with the community

On Discord, you can share:

 - What is your overall approach to your assessment?
 - Which risks you prioritize first?

If you want your work to be public, add your file to:

- `community/solutions/dayNN/` by copying the template:  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- then open a pull request.

In `journey/your-name.md`, write a short entry for today:

 - Are you going to use a risk-based approach in your daily code?
 - How would you document a roadmap to make it the most efficient?

**Let's avoid risks at all cost! ☑️**
