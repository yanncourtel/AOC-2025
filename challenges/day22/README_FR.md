# Jour 22 – Réparer le processeur de boucles temporelles ➰🕓

> _22 décembre 2025 – Réparer le processeur de boucles temporelles_

![snippet of the day](img/day22.png)

Au 22ᵉ jour, nous entrons dans la dernière phase de l’aventure : celle où nous réparons le temps lui‑même.

Le premier système qui pose problème est le **processeur de boucles temporelles**.  
Il ne les traite plus correctement. Et les évenements sont perdus dans des paradoxes temporels.

Ta mission est de **corriger le bug ET de migrer le système vers une stack non supportée** par l’exercice.

---

## Défi — Corriger et migrer le système défaillant

Aujourd’hui, l’exercice est particulier.  
Tu dois corriger la façon dont les boucles temporelles sont traitées.

La solution n’est **pas** d’éviter le parallélisme — nous avons BESOIN de traiter plusieurs réalités en même temps pour des raisons de performance.

**C’est de la modernisation logicielle :**  
Passer de langages qui laissent la porte ouverte aux paradoxes temporels  
à des langages qui les empêchent par conception.

Les langages non supportés vers lesquels tu peux migrer le système :

- Kotlin  
- Rust  
- F#  

---

## Zone de jeu

Pour la dernière partie de l’aventure — les activations finales —  
nous allons dans `/exercises` et les dayNN.

Tu peux ensuite choisir la stack que tu veux utiliser comme **stack principale** :

- `exercises/day22/<your-stack>/`

C’est ton terrain de jeu.

---

## Partage avec la communauté

Sur Discord, tu peux partager :

- Quelle stack tu as choisie, et pourquoi ?

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- Quels avantages t’a apporté la stack que tu as choisie ?  
- Pourquoi le parallélisme et l’asynchronisme doivent-ils être utilisés avec précaution ?

**Modernisons le processeur de boucles temporelles. ➰🕓**
