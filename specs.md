## Question 1 - Spécifications fonctionnelles

**Contexte :** La fonction reçoit trois entiers $a$, $b$, et $c$ représentant les longueurs des côtés d'un triangle.

| Exigence | Description          | Détail                                                                                                                       | Retour |
|-         |-                     |-                                                                                                                             |-       |
| $E_1$    | Pas un triangle      | Si au moins un côté est ≤ 0, ou si l'inégalité triangulaire n'est pas strictement respectée (a+b ≤ c ou a+c ≤ b ou b+c ≤ a). | 0      |
| $E_2$    | Triangle scalène     | Si les trois côtés sont strictement positifs, l'inégalité triangulaire est respectée, et tous les côtés sont différents.     | 1      |
| $E_3$    | Triangle isoscèle    | Si les trois côtés sont strictement positifs, l'inégalité triangulaire est respectée, et exactement deux côtés sont égaux.   | 2      |
| $E_4$    | Triangle équilatéral | Si les trois côtés sont strictement positifs, l'inégalité triangulaire est respectée, et les trois côtés sont égaux.         | 3      |

**Conditions de validité d'un triangle :**

Tous les côtés doivent être strictement positifs :

$$
\begin{cases}
    a > 0\\
    b > 0\\
    c > 0
\end{cases}
$$

L'inégalité triangulaire doit être vérifiée :

$$
\begin{cases}
    a + b > c\\
    a + c > b\\
    b + c > a
\end{cases}
$$

## Question 2 - Suite de tests

**Tableau de classes d'équivalence**

|                        | | |
|-                       |-|-|
| Valeur de $a$          | $a > 0$ (CV1)                                               | $a \leq 0$ (CI1)                                                |
| Valeur de $b$          | $b > 0$ (CV2)                                               | $b \leq 0$ (CI2)                                                |
| Valeur de $c$          | $c > 0$ (CV3)                                               | $c \leq 0$ (CI3)                                                |
| Inégalité triangulaire | $\begin{cases}a+b > c\\ a+c > b\\ b+c > a\end{cases}$ (CV4) | $\neg\begin{cases}a+b > c\\ a+c > b\\ b+c > a\end{cases}$ (CI4) |

### 2.2 - Valeurs aux limites

|     |      |     |     |
|-    |-     |-    |-    |
| CI2 |      |     |     |
| CI3 |      |     |     |
| CV3 | *J1  | *J2 |     |
|     | CV4  | CV5 | CV4 |