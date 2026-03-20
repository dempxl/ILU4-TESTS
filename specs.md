## Question 1 - Spécifications fonctionnelles

La fonction `TriangleClassifier(a, b, c)` prend trois entiers représentant les longueurs des côtés d'un triangle et retourne un entier selon le tableau suivant :

| Valeur retournée | Condition |
|-|-|
| 0 | Les entrées ne forment pas un triangle valide (côté ≤ 0 ou inégalité triangulaire non respectée) |
| 1 | Triangle scalène : tous les côtés sont différents et la figure est un triangle valide |
| 2 | Triangle isoscèle : exactement deux côtés sont égaux et la figure est un triangle valide |
| 3 | Triangle équilatéral : les trois côtés sont égaux |

**Conditions de validité d'un triangle :**

Tous les côtés doivent être strictement positifs :

$$
a > 0, b > 0, c > 0
$$

L'inégalité triangulaire doit être vérifiée :

$$
a + b > c, a + c > b, b + c > a
$$

## Question 2 - Suite de tests

### 2.1 - Classes d'équivalence

**Classes invalides (résultat attendu = 0)**

| ID  | Description                             | Exemple (a, b, c) | Résultat attendu |
|-    |-                                        |-                  |-                 |
| CI1 | Un côté négatif (a < 0)                 | (-1, 3, 3)        | 0                |
| CI2 | Un côté nul (a = 0)                     | (0, 3, 3)         | 0                |
| CI3 | Inégalité triangulaire violée : a+b ≤ c | (1, 2, 5)         | 0                |
| CI4 | Inégalité triangulaire violée : a+c ≤ b | (1, 5, 2)         | 0                |
| CI5 | Inégalité triangulaire violée : b+c ≤ a | (5, 1, 2)         | 0                |

**Classes valides**

| ID  | Description                             | Exemple (a, b, c) | Résultat attendu |
|-    |-                                        |-                  |-                 |
| CV1 | Triangle équilatéral (a == b == c)      | (3, 3, 3)         | 3                |
| CV2 | Triangle isoscèle (a == b)              | (3, 3, 5)         | 2                |
| CV3 | Triangle isoscèle (a == c)              | (3, 5, 3)         | 2                |
| CV4 | Triangle isoscèle (b == c)              | (5, 3, 3)         | 2                |
| CV5 | Triangle scalène (a != b != c)          | (2, 3, 4)         | 1                |

### 2.2 - Valeurs aux limites

|          |        |        |          |
|-         |-       |-       |-         |
| $C_{I2}$ |        |        |          |
| $C_{I3}$ |        |        |          |
| $C_3$    | $*J_1$ | $*J_2$ |          |
|          | $C_4$  | $C_5$  | $C_{I4}$ |