# Day 19 - North Pole Companies

> _December 19, 2025 - North Pole Companies_

![snippet of the day](img/day19.png)

  ==> **Loading Fractured Memory** <==

On the 19th day, the memory brings you in an elven workshop working for Santa.

The workshop uses various elf delivery companies to distribute toys. Each company employs elves from different regions (North Pole, Nordic, Alpine, Arctic). Refer to the [tax rates](./TAX_RATES.md).

You need to add regional tax calculation to the existing invoicing system.

## Challenge — Add the taxes amount to the invoice.

Today is a North Pole version of the Theatrical player kata. 
The approval testing in the test suite serves well as functional tests.

You are to use the approval testing to drive your refactoring.

Here is a suggested approach:
 1) Create a NEW approval file showing the expected output WITH taxes (this is your specification)
 2) Add a new test method that loads tax rates and uses this approval file
 3) The test will fail. Implement the feature to make it pass (you can overload the main method)
 4) Both tests should pass. Refactor at will!

<details>
<summary>💡 Expected output format for the sample data (click to expand)</summary>

Your new approval file should add tax lines after each delivery:
```
Invoice for Toys-R-Us North America
 Rudolph Express Delivery: $600.00 (120 packages)
   Tax (North Pole - 0%): $0.00
 Jingle's Standard Service: $960.00 (80 packages)
   Tax (Nordic Region - 15%): $144.00
 Frosty's Fast Fleet: $725.00 (95 packages)
   Tax (Alpine Region - 20%): $145.00
Subtotal: $2,285.00
Total Tax: $289.00
Amount owed is $2,574.00
You earned 129 loyalty points
```

Tax rates: North Pole (0%), Nordic (15%), Alpine (20%), Arctic (10%)

</details>

---

## Where to play

For the exploration of the fractured memories, since they are not your own memories
We will go to /exercises and the dayNN

You can then pick the stack you want to use as your **main stack**.

- `exercises/day19/<your-stack>/`

This is your playground.

---

## Share with the community

On Discord, you can share:

 - Which approach did you choose for this exercise?
 - Did approval testing prove to be a strength or a hindrance at first?

If you want your work to be public, add your file to:

- `community/solutions/dayNN/` by copying the template:  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- then open a pull request.

In `journey/your-name.md`, write a short entry for today:

 - How and when did you decide to refactor?
 - Did you add any other tests while refactoring?

**Let's add the missing feature. 🧾💲**
