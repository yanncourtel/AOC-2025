# Jour 17 – Le répartiteur de cadeaux perdu du Père Noël

> _17 décembre 2025 – Le répartiteur de cadeaux perdu du Père Noël_

![snippet of the day](img/day17.png)

  ==> **Chargement de mémoire fracturée** <==

Au 17ᵉ jour, ta mémoire fracturée t’emmène juste à côté du bureau du Père Noël !  
Et il est furieux ! Il essaie de comprendre son code du répartiteur de cadeaux.

Il a ajouté quelques commentaires hésitants, mais le code a l’air obfusqué, comme récupéré depuis un binaire décompilé.

**Tu dois l’aider à résoudre cette énigme.**

Il t’a laissé une note métier pour t’aider à comprendre ce que le code est censé faire.

---

## Défi — Transformer le code pour le rendre utilisable

Aujourd’hui, ce n’est pas vraiment un refactoring « classique ».

Tu vas travailler avec du **mauvais code obfusqué** et essayer d’en restaurer la compréhension.

**Indice :** utilise l’IA comme point de départ, puis retravaille le code à partir de là.

---

## Note métier (du Père Noël)

> Je garde une liste du nombre d’exemplaires que j’ai de chaque cadeau dans l’atelier,  
> et une liste d’enfants avec leurs listes de souhaits (classées par ordre de préférence).  
> Quand je lance le répartiteur, je lui donne le **nombre maximum de cadeaux par enfant**.  
> Il doit ensuite assigner les cadeaux aux enfants dans l’ordre dans lequel ils ont été enregistrés,  
> en essayant d’abord les éléments de la wishlist, puis en utilisant le stock restant,  
> et retourner une liste indiquant quel enfant a reçu quel cadeau.

C’est tout ce dont le Père Noël est sûr.  
Le reste est dans du code décompilé.

---

## Zone de jeu

Pour l’exploration des mémoires fracturées, comme ce ne sont pas **tes** propres souvenirs,  
nous allons dans `/exercises` et les dayNN.

Tu peux ensuite choisir la stack que tu veux utiliser comme **stack principale** :

- `exercises/day17/<your-stack>/`

C’est ton terrain de jeu.

---

## Partage avec la communauté

Sur Discord, tu peux partager :

- Comment tu as abordé la première version du code ?  
- Quelle IA tu as utilisée pour t’aider, et avec quel type de prompt ?

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- Comment as-tu réussi à extraire de la connaissance d’un code aussi mauvais ?  
- Comment pourrais-tu appliquer ça dans ta vie pro de tous les jours ?

**Rebâtissons le répartiteur ensemble. 🎅📦**
