# Jour 6 – Construire un pipeline pur

![extrait du jour](img/day06.png)

> **Fragment de mémoire – 2023 · Jour 7**
> Je me souviens de cette méthode run.
> Elle faisait tout à la fois : lire, transformer, enregistrer, écrire...
> Nous l'avons divisée en plusieurs méthodes plus petites, et cela semblait mieux.
> Mais il manquait encore quelque chose. Il y avait trop d'états... ...
> Nous avons besoin d'un pipeline plus propre. Fragment de mémoire – 2023 · Jour 7
> Je me souviens de cette méthode d'exécution.
> Elle faisait tout à la fois : lire, transformer, enregistrer, écrire...
> Nous l'avons divisée en méthodes plus petites, et cela nous a semblé mieux.
> Mais il manquait encore quelque chose... Trop d'états... ...
> *Nous avons besoin d'un pipeline plus propre.*


Au sixième jour du voyage, nous revisitons le **souvenir de 2023 après une semaine**, où l'objectif initial était de simplifier la méthode « run » en extrayant le bon comportement.

Cette fois-ci, nous allons plus loin :

> **Nous visons à rendre « run » (ou son cœur) aussi *référentiellement transparent* que possible.**  

En bref : mêmes entrées → mêmes sorties.
Pas d'état caché. Pas d'effets secondaires surprenants.

---

## Défi — Transformez la méthode « run » en un pipeline pur

 💡Un indice pour le défi d'aujourd'hui💡

Une fonction pure devrait ressembler à ceci :

```text
   Result runPure(Input input)
   ```

   Vous devriez pouvoir **remplacer un appel** à cette fonction par son résultat  
   sans modifier le comportement du programme.

**Soyez conscient des modifications que vous apportez aux tests**

---

## Où jouer

Choisissez la pile que vous souhaitez utiliser comme **pile principale** cette année et ouvrez la mémoire du jour 7 de 2023 :

- `memories/2023/exercises/<votre-pile>/day07`

C'est votre terrain de jeu.

Si vous manquez de temps :

- concentrez-vous sur la **transformation de base** (le cœur de la fonction `run`),  
- extrayez juste ce qu'il faut pour obtenir une fonction pipeline clairement **pure**.

Laissez le pipeline raconter l'histoire ➰💧

---

## Partagez avec la communauté

Sur Discord, vous pouvez partager par exemple :

- un extrait avant/après de votre méthode `run`,
- la signature de votre fonction de pipeline pure,
- une brève note sur ce que vous avez poussé vers les bords (états mutables, sorties, etc.).

Si vous souhaitez que votre travail soit public, ajoutez votre fichier à :

- `community/solutions/dayNN/` en copiant le modèle :  
  `community/solutions/TEMPLATE-[remplacer_par_votre_nom].md`  
- puis ouvrez une demande d'extraction.

Dans `journey/votre-nom.md`, rédigez une brève entrée pour aujourd'hui :

- Quels effets secondaires ai-je trouvés dans `run` ?  
- À quoi ressemble mon **pipeline pur** (à un niveau élevé) ?  
- Le fait de rendre les choses plus transparentes sur le plan référentiel a-t-il changé ma façon de tester ou de raisonner sur le code ?  

Chaque fois que vous rendez le code un peu plus « pur »,  
vous facilitez votre retour dans la boucle temporelle à l'avenir. ⏳
