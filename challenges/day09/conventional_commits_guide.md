# Conventional Commits Guide

## Understanding Conventional Commits

Before you start refactoring, let's understand **conventional commits** - a standardized format for commit messages.

### The Format
```
<type>(<scope>): <description>

[optional body]

[optional footer]
```

### Common Types for Refactoring

| Type | When to use | Example |
|------|-------------|---------|
| `refactor` | Code changes that neither fix bugs nor add features | `refactor(sleigh): extract discount calculation` |
| `style` | Formatting, naming, whitespace (no logic change) | `style(sleigh): rename quantityAsInt to itemCount` |
| `test` | Adding or modifying tests only | `test(sleigh): add test for edge case` |
| `docs` | Documentation changes only | `docs(sleigh): add javadoc to handleOffers` |

**For this exercise, you'll mostly use `refactor` and occasionally `style`.**

### Scope
The scope indicates **what part** of the codebase changed. In this exercise:
- `(sleigh)` - changes to ShoppingSleigh
- `(elf)` - changes to ChristmasElf
- `(receipt)` - changes to Receipt

### Description
The description is a **short, imperative statement** of what changed:

✅ **Good descriptions:**
- `extract aggregateProductQuantities method`
- `rename quantityAsInt to itemCount`
- `remove unnecessary discount validation`

❌ **Poor descriptions:**
- `made some changes` (too vague)
- `extracted a method` (what method?)
- `Fixed the code` (not descriptive)

### When to Use the Body

Add a body when you need to explain **WHY** or provide context:

```
refactor(sleigh): extract calculateThreeForTwoDiscount method

Isolates the 3-for-2 discount logic to make it testable independently
and reduce complexity in handleOffers. This will make it easier to
add new discount types in the future.
```

**Rules of thumb:**
- **Simple changes** (renames, formatting) → description only
- **Extractions or restructuring** → add body explaining benefit
- **Non-obvious changes** → definitely add body

### Common Pitfalls

🚫 **Don't mix multiple changes in one commit:**
```
❌ refactor(sleigh): extract methods and rename variables
```
Instead, make separate commits:
```
✅ refactor(sleigh): extract aggregateProductQuantities method
✅ style(sleigh): rename quantityAsInt to itemCount
```

🚫 **Don't write past tense:**
```
❌ refactor(sleigh): extracted the discount calculation
```
Use imperative mood (like you're giving a command):
```
✅ refactor(sleigh): extract discount calculation
```

🚫 **Don't write commit messages that are too long:**
```
❌ refactor(sleigh): extract the product quantity aggregation into 
    a separate method called aggregateProductQuantities
```
Keep the description under 50 characters when possible:
```
✅ refactor(sleigh): extract aggregateProductQuantities method
```

## ⚠️ Common Conventional Commit Mistakes

Learn from these common errors:

### Mistake #1: Mixing Multiple Changes
```
❌ refactor(sleigh): extract methods, rename variables, and fix logic
```
**Why it's wrong:** One commit should do ONE thing. This makes it hard to review and impossible to revert partially.

**Better approach:** Make 3 separate commits
```
✅ refactor(sleigh): extract aggregateProductQuantities method
✅ style(sleigh): rename quantityAsInt to itemCount  
✅ fix(sleigh): correct discount calculation for edge case
```

### Mistake #2: Using Wrong Type
```
❌ feat(sleigh): extract discount calculation method
```
**Why it's wrong:** Extracting a method isn't a new feature—it's a refactoring. Types matter for automated tooling (changelogs, semantic versioning).

**Better:**
```
✅ refactor(sleigh): extract discount calculation method
```

### Mistake #3: Vague Descriptions
```
❌ refactor(sleigh): improve code
❌ refactor(sleigh): make changes
❌ refactor(sleigh): refactor handleOffers
```
**Why it's wrong:** These tell you nothing about WHAT changed. Six months from now, you won't know what this commit did.

**Better:**
```
✅ refactor(sleigh): extract aggregateProductQuantities method
✅ refactor(sleigh): replace nested conditionals with strategy pattern
✅ refactor(sleigh): split handleOffers into smaller methods
```

### Mistake #4: Inconsistent Scope
```
❌ refactor(ShoppingSleigh): extract method
❌ refactor(shopping-sleigh): extract method
❌ refactor(sleigh): extract method
```
**Why it's wrong:** Scopes should be consistent across your project. Pick a convention and stick to it.

**Better:** Choose one format and use it everywhere
```
✅ refactor(sleigh): extract method  # lowercase, short
```

### Mistake #5: Past Tense
```
❌ refactor(sleigh): extracted the discount calculation method
❌ refactor(sleigh): renamed variables for clarity
```
**Why it's wrong:** Conventional commits use imperative mood (like Git itself: "Merge", "Revert").

**Better:**
```
✅ refactor(sleigh): extract discount calculation method
✅ style(sleigh): rename variables for clarity
```

### Mistake #6: Too Long Description
```
❌ refactor(sleigh): extract the product quantity aggregation logic into a separate private method called aggregateProductQuantities
```
**Why it's wrong:** Descriptions should be concise. Details go in the body.

**Better:**
```
✅ refactor(sleigh): extract aggregateProductQuantities method

Separates product quantity aggregation from discount calculation
to improve method readability and single responsibility.
```

### Mistake #7: Not Testing After Each Commit
```
You make 3 changes, then run tests
Tests fail 🔴
Now you don't know which change broke things!
```
**Why it's wrong:** The whole point of incremental commits is that each one is a safe, working state.

**Better:**
```
1. Make change #1 → Run tests ✅ → Commit
2. Make change #2 → Run tests ✅ → Commit  
3. Make change #3 → Run tests ✅ → Commit