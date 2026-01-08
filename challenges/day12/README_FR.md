# Jour 12 – Politique de mot de passe pour les humains

> _12 décembre 2025 – Politique de mot de passe pour les humains_

![snippet of the day](img/day12.png)

  ==> **Chargement de mémoire fracturée** <==

Au douzième jour du voyage, tu aides les elfes à ouvrir leur marché de Noël.

Cette année, c’est une occasion spéciale : ils l’ouvrent avec des humains pour la toute première fois.

... mais ils ne leur font pas confiance.

Pour accéder au bâtiment, les commerçants humains doivent saisir leur mot de passe et le système de vérification n’est pas prêt.  
En effet, les elfes ont déjà une politique de mot de passe en place, mais pas les humains.

Peux-tu les aider ? (Les spécifications détaillées ci-dessous)

---

## Défi — Implémenter une politique de mot de passe pour les humains

Aujourd’hui, tu démarres juste après l’implémentation de la politique de mot de passe elfique de base.

L’idée est de réfléchir à **comment** tu vas implémenter une autre politique sans casser l’ancienne.

 💡INDICE💡 : Tu commences la journée en **phase pré-refactoring** !

> Focus : boucle TDD pure — ROUGE / VERT / REFACTOR.

---

## Exigences

### 1. Politique de mot de passe des elfes (souple) — **ce qui est déjà implémenté**

Un mot de passe **elfe** est valide si :

- Il contient **au moins 6 caractères**
- Il contient **au moins une lettre majuscule**
- Il contient **exactement un chiffre**

Tout le reste est autorisé (pour cet exercice, on ne se préoccupe pas encore des symboles ou caractères invalides).

Exemples qui **doivent être valides** :

- `Abcde1`
- `ELfMAr1`

Exemples qui **doivent être invalides** :

- `""` (vide) → trop court  
- `"Abc1"` → trop court  
- `"abcdef"` → aucune majuscule, aucun chiffre  
- `"abcde1"` → aucune majuscule  
- `"ABCDEF"` → aucun chiffre  
- `"Abcde12"` → plus d’un chiffre  

Les tests fournis documentent ce comportement et doivent tous passer avec l’implémentation initiale.

---

### 2. Politique de mot de passe des humains (stricte) — **ce que tu vas implémenter**

Les humains doivent utiliser une politique de mot de passe **plus stricte**.

Un mot de passe **humain** est valide si :

- Il contient **au moins 8 caractères**
- Il contient **au moins une lettre majuscule**
- Il contient **au moins une lettre minuscule**
- Il contient **au moins un chiffre**
- Il contient **au moins un caractère spécial** parmi cette liste :
  - `.`, `*`, `#`, `@`, `$`, `%`, `&`
- Il ne contient **aucun caractère invalide** :
  - seulement des lettres, des chiffres, et les caractères spéciaux listés ci-dessus sont autorisés.

Exemples qui doivent être valides :

- `P@ssw0rd`
- `Advent0fCraft&`

Exemples qui doivent être invalides :

- Trop court : `"xxxxxxx"`  
- Aucune majuscule : `"adventofcraft"`, `"p@ssw0rd"`  
- Aucune minuscule : `"ADVENTOFCRAFT"`, `"P@SSW0RD"`  
- Aucun chiffre : `"Adventofcraft"`, `"P@sswOrd"`  
- Aucun caractère spécial : `"Adventof09craft"`, `"PAssw0rd"`  
- Caractère invalide : `"Advent@of9Craft¨"`, `"P@ssw^rd"`  

(Ces exemples sont tirés de la version stricte originale que tu vas recréer.)

---

## Zone de jeu

Pour l’exploration des mémoires fracturées, comme ce ne sont pas **tes** propres souvenirs,  
nous allons dans `/exercises` et les dayNN.

Tu peux ensuite choisir la stack que tu veux utiliser comme **stack principale** :

- `exercises/day12/<your-stack>/`

C’est ton terrain de jeu.

---

## Partage avec la communauté

Sur Discord, tu peux partager :

- En quoi partir d’une implémentation **simple** réservée aux elfes a influencé ton design ?  
- Quels doubles de test as-tu finalement créés (fake, stub, spy, …) ?  
- Est-ce que le design de ton code de production est devenu plus simple ou plus difficile à faire évoluer ?

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- Lors de l’ajout de la politique humaine :
  - Quels tests as-tu écrits en premier ?
  - As-tu réutilisé des idées / patterns issus des tests des elfes ?
- À partir de quel moment la duplication est-elle devenue suffisamment douloureuse pour justifier un refactoring ?

**Faisons en sorte que ce soit la plus belle expérience de marché. 🎅**
