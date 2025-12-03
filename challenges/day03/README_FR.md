# Jour 03 – SOLID sous un autre angle

> **Fragment de mémoire – 2024 · Jour 12**  
> Je crois que je me suis un peu emballé à un moment...  
> J’ai commencé à tout collecter.  
> Des listes de listes, des options dans des collections,  
> des données dont personne n’avait peut‑être jamais besoin.  
> Puis le Père Noël m’a demandé :  
> « Pourquoi tu collectes tout ça ? »  
> ...  
> *Je me souviens avoir simplifié le design une fois.*  
> *Mais je ne me rappelle plus quels principes j’avais suivis…*

Pour ce troisième jour du voyage, nous revisitons un souvenir de 2024 où une question simple revenait sans cesse :

> **Est‑ce que j’avais vraiment besoin de faire tout ça ?**

L’objectif du jour est de *vivre* SOLID sous deux modes sur un petit morceau de code :

1. **SOLID “à la lettre”** – Pousser les principes un peu plus loin que d’habitude dans notre code.  
2. **SOLID “pragmatique”** – Ne garder que ce qui aide vraiment.

Chaque phase de ton expérience sera reflétée dans tes commits.

---

## Défi — Appliquer SOLID à la lettre, puis faire la paix avec lui

L’idée aujourd’hui est de sur‑concevoir le design en suivant SOLID à la lettre, presque sans te poser de questions. Choisis un petit hotspot dans ton code.

Puis fais une pause : note tes observations, tes métriques de complexité, les points de douleur, etc.

Ensuite, *pense* SOLID et reviens vers un design plus pragmatique.

Le résultat final n’a **pas** besoin d’être parfaitement SOLID.  
Il a juste besoin d’être **plus clair et plus intentionnel** que ce avec quoi tu as commencé.

Si tu as encore l’outil de complexité du Jour 1, tu peux éventuellement comparer la version d’origine avec ta version finale pour voir si la complexité a augmenté, baissé ou simplement été déplacée.

Partage ton code et tes observations au fil de l’eau.

---

## Zone de jeu

Choisis la stack que tu veux utiliser comme **stack principale** cette année et ouvre le souvenir du Jour 12 de 2024. Pour une journée plus douce, tu peux plutôt choisir le Jour 12 de 2023 :

- `memories/2024/exercises/<your-stack>/day12`

| Mode             | Mémoire                                        | Recommandé pour…                          |
|------------------|-----------------------------------------------|-------------------------------------------|
| 🧭 **Standard**  | `memories/2024/exercises/<your-stack>/day12`  | Plus d’une raison de refactorer           |
| 🟢 **Facile**    | `memories/2023/exercises/<your-stack>/day12`  | Un exercice simple centré sur l’OCP       |

Laisse le design respirer ➰💫

---

## Partage avec la communauté

Poste ta solution sur Discord et reçois des retours instantanés de la communauté. N’hésite pas à donner toi aussi un feedback respectueux aux autres.

Si tu veux rendre ta solution publique, ajoute ton fichier dans le dossier  
`community/solutions/dayNN/` en copiant le template situé ici :  
`community/solutions/TEMPLATE-[replace_with_your_name].md`  
puis ouvre une pull request.

Utilise le fichier `journey/your-name.md` et écris une entrée pour aujourd’hui :

- Qu’est‑ce qui a changé quand j’ai appliqué SOLID “à la lettre” ?  
- Où est‑ce que cela a clairement amélioré le code ?  
- Où est‑ce que cela a alourdi les choses, et qu’est‑ce que j’ai simplifié dans le commit final ?  
- Est‑ce que le fait de modéliser le comportement plus explicitement m’a aidé ?
