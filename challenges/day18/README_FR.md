# Jour 18 – Traîneau à risques

> _18 décembre 2025 – Traîneau à risques_

![snippet of the day](img/day18.png)

  ==> **Chargement de mémoire fracturée** <==

Au 18ᵉ jour, ta mémoire fracturée te projette dans un soi-disant monde parfait !  
Un monde dans lequel le mauvais code est devenu illégal.

Il y a un mois, le Gouvernement Mondial a adopté le **Code Safety Act**.

Tous les systèmes logiciels doivent éliminer les modèles de risque interdits avant le 25 décembre, sous peine de **mise à l’arrêt obligatoire**.

Le système de contrôle du traîneau du Père Noël a été signalé pour **multiples violations**.  
Tu vas devoir refactorer suffisamment de risques pour être conforme et garder Noël sur les rails !

---

## Défi — Rendre le système du traîneau conforme aux risques

Aujourd’hui, l’exercice te demande d’**évaluer les risques** dans un système et de guider ton refactoring à partir de cette analyse.

Une bonne partie de ton temps devrait être consacrée à **analyser la base de code** et à comprendre :
- quels risques sont présents,
- à quel point ils sont critiques.

Ce n’est pas tant une question de changer beaucoup de code que de **documenter une feuille de route de refactoring claire et complète**.

**Indice :** l’IA est un bon point de départ pour évaluer les risques et t’aider à les prioriser.

---

## 📊 Registre de risques gouvernemental

| ID Risque | Description | Valeur CP | Difficulté |
|-----------|-------------|-----------|------------|
| **R1** | Champs d’état publics mutables (`status`, `action`) | 5 | 🟢 Facile |
| **R2** | Manipulation directe des internes d’une dépendance | 3 | 🟢 Facile |
| **R3** | Aucune validation des transitions d’état | 4 | 🟡 Moyen |
| **R4** | Nombres magiques sans explication | 2 | 🟢 Facile |
| **R5** | Gestion des exceptions laissant l’état incertain | 4 | 🟡 Moyen |
| **R6** | Absence de garantie sur la séquence de cycle de vie | 3 | 🟡 Moyen |
| **R7** | Aucun journal (“audit trail”) des opérations | 2 | 🟢 Facile |

**Total audité :** 23 CP  
**Minimum requis pour passer :** 10 CP

Garde en tête que 10 CP est un **minimum** et qu’il peut être pertinent d’en traiter davantage.

---

## Zone de jeu

Pour l’exploration des mémoires fracturées, comme ce ne sont pas **tes** propres souvenirs,  
nous allons dans `/exercises` et les dayNN.

Tu peux ensuite choisir la stack que tu veux utiliser comme **stack principale** :

- `exercises/day18/<your-stack>/`

C’est ton terrain de jeu.

---

## Partage avec la communauté

Sur Discord, tu peux partager :

- Quelle a été ton approche globale pour ton évaluation des risques ?  
- Quels risques as-tu choisi de prioriser en premier, et pourquoi ?

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- Vas-tu utiliser une approche guidée par les risques dans ton code au quotidien ?  
- Comment documenterais-tu une roadmap pour qu’elle soit la plus efficace possible ?

**Évitons les risques à tout prix ! ☑️**
