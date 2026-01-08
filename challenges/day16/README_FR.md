# Jour 16 – Le code comme documentation

> _16 décembre 2025 – Le code comme documentation_

![snippet of the day](img/day16.png)

  ==> **Chargement de mémoire fracturée** <==

Au 16ᵉ jour, ta mémoire fracturée t’emmène directement au **Conseil International d’Urgence de Noël** !

L’ambiance est tendue. Des représentant·e·s de 12 pays sont présents, et ils ne sont pas contents.

**« Tokyo a encore eu le Père Noël au mauvais moment ! »**  
**« La logistique de Mumbai était complètement décalée ! »**  
**« On ne peut pas continuer à faire tourner Noël sur du code que personne ne comprend ! »**

Le système a été écrit en 1987 et il n’y a **aucune documentation**.  
Le code fonctionne — la plupart du temps — mais tous les quelques années, il y a un « incident ».  
L’année dernière, c’était Mumbai. L’année d’avant, Tokyo.  
De petits décalages horaires qui provoquent d’énormes migraines logistiques.

**Le code n’est pas cassé. Le code est *terrifiant*.**

---

## Défi — Restaurer le savoir perdu

Aujourd’hui, ta première mission n’est pas de refactorer le code.  
Ils n’ont pas besoin que tu le réécrives (pas encore, en tout cas).

Ils ont d’abord besoin de **comprendre ce qu’il fait**.

L’équipe t’a laissé un backlog rempli de tickets à documenter.  
**Tu dois tous les documenter.**

Souviens-toi : le conseil ne lit pas le code.  
Ils ont besoin d’une vraie documentation (utilise une forme de documentation visuelle, si possible).

**Indice :** quel est le plus court cycle de feedback ?  
Est-ce de lire le code, d’exécuter le programme, d’écrire un test ?

---

## Le backlog de tickets

Ton équipe a collecté des questions issues de différents incidents.  
Utilise-les comme point de départ :

### 🎫 Tickets d’investigation

**TICKET-101 : Pourquoi Hawaï est le 25 décembre mais New York le 24 décembre ?**  
- Hawaï : UTC-10  
- New York : UTC-5  
- Les deux reçoivent 23h… mais pas le même jour ? Quel est le schéma derrière ça ?

**TICKET-102 : Pourquoi Londres est à 20h mais New York à 23h ?**  
- Londres : UTC+0, reçoit 20h le 24 décembre  
- New York : UTC-5, reçoit 23h le 24 décembre  
- Tous deux la veille de Noël, mais avec 3 heures d’écart. Pourquoi ?

**TICKET-103 : Que se passe-t-il exactement pour UTC-5 et UTC+0 ?**  
- Ces fuseaux semblent être des points de frontière. Comment sont-ils traités ?  
- Sont-ils regroupés avec les zones avant ou après eux ?

**TICKET-104 : Comment le système gère-t-il les fuseaux à demi-heure ?**  
- Mumbai : UTC+5.5  
- Terre-Neuve : UTC-3.5  
- L’incident de 2023 est arrivé avec l’un de ceux-là…

**TICKET-105 : Quel est l’ensemble complet des règles ?**  
- Peux-tu documenter toutes les règles métier pour les fuseaux horaires de -12 à +14 ?  
- Combien de comportements distincts existe-t-il ?

---

## Zone de jeu

Pour l’exploration des mémoires fracturées, comme ce ne sont pas **tes** propres souvenirs,  
nous allons dans `/exercises` et les dayNN.

Tu peux ensuite choisir la stack que tu veux utiliser comme **stack principale** :

- `exercises/day16/<your-stack>/`

C’est ton terrain de jeu.

---

## Partage avec la communauté

Sur Discord, tu peux partager :

- Ce que tu as décidé de faire pour comprendre le code.  
- Si tu as changé d’approche en cours de route (tests, exécution, diagrammes, etc.).

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- Les tests comme documentation, est-ce un réflexe automatique pour toi ?  
- Comment t’es-tu senti·e en faisant du pré-refactoring sur du code legacy existant (sans tout casser) ?

**Bonne chance pour restaurer le savoir perdu ! 📜**
