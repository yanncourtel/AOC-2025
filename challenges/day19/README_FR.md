# Jour 19 – Entreprises du Pôle Nord

> _19 décembre 2025 – Entreprises du Pôle Nord_

![snippet of the day](img/day19.png)

  ==> **Chargement de mémoire fracturée** <==

Au 19ᵉ jour, la mémoire te transporte dans un atelier d’elfes travaillant pour le Père Noël.

L’atelier utilise plusieurs compagnies de livraison elfiques pour distribuer les jouets.  
Chaque compagnie emploie des elfes de différentes régions (Pôle Nord, Nordique, Alpine, Arctique). Référez-vous à la [grille des taxes](./TAX_RATES.md).

Tu dois ajouter le calcul des taxes régionales au système de facturation existant.

---

## Défi — Ajouter le montant des taxes à la facture

Aujourd’hui, tu travailles sur une version "Pôle Nord" du Theatrical Player kata.  
Les approvals tests fournis agissent comme des tests fonctionnels.

Tu vas utiliser ces approvals tests pour guider ton refactoring.

Voici une approche suggérée :

1. Crée un **NOUVEAU fichier approvals** montrant la sortie attendue **AVEC** les taxes (c’est ta spécification).  
2. Ajoute une nouvelle méthode de test qui charge les taux de taxes et utilise ce nouveau fichier approvals.  
3. Le test va échouer. Implémente la fonctionnalité pour le faire passer (tu peux surcharger la méthode principale si besoin).  
4. Les deux tests doivent passer. Refactore ensuite autant que nécessaire !  

<details>
<summary>💡 Format de sortie attendu pour les données d’exemple (cliquer pour dérouler)</summary>

Ton nouveau fichier pour les tests approvals doit ajouter des lignes de taxes après chaque livraison :

```text
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

Taux de taxes : Pôle Nord (0%), Nordique (15%), Alpine (20%), Arctique (10%).

</details>

---

## Zone de jeu

Pour l’exploration des mémoires fracturées, comme ce ne sont pas **tes** propres souvenirs,  
nous allons dans `/exercises` et les dayNN.

Tu peux ensuite choisir la stack que tu veux utiliser comme **stack principale** :

- `exercises/day19/<your-stack>/`

C’est ton terrain de jeu.

---

## Partage avec la communauté

Sur Discord, tu peux partager :

- Quelle approche as-tu choisie pour cet exercice ?  
- Est-ce que les approvals tests t'ont semblé être une force ou un frein au début ?

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- Comment et à quel moment as-tu décidé de refactorer ?  
- As-tu ajouté d’autres tests pendant ton refactoring ?

**Ajoutons la fonctionnalité manquante. 🧾💲**
