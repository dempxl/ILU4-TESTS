## Question 1 - Spécifications fonctionnelles

**Contexte :** La fonction reçoit trois entiers $a$, $b$, et $c$ représentant les longueurs des côtés d'un triangle.

| Exigence | Description          | Détail                                                                                                                       | Retour |
|-         |-                     |-                                                                                                                             |-       |
| E1.1a    | $a$ négatif          | $a < 0$, ce qui rend impossible la construction d'un triangle.                                                               | 0      |
| E1.2b    | $b$ négatif          | $b < 0$, ce qui rend impossible la construction d'un triangle.                                                               | 0      |
| E1.3c    | $c$ négatif          | $b < 0$, ce qui rend impossible la construction d'un triangle.                                                               | 0      |
| E1.4a    | $a$ nul              | $a = 0$, ce qui rend impossible la construction d'un triangle.                                                               | 0      |
| E1.4b    | $b$ nul              | $b = 0$, ce qui rend impossible la construction d'un triangle.                                                               | 0      |
| E1.4c    | $c$ nul              | $c = 0$, ce qui rend impossible la construction d'un triangle.                                                               | 0      |
| E1.5c    | Inég $a+b$ non resp. | $a+b \leq c$, ce qui rend impossible la construction d'un triangle.                                                          | 0      |
| E1.5a    | Inég $b+c$ non resp. | $b+c \leq a$, ce qui rend impossible la construction d'un triangle.                                                          | 0      |
| E1.5b    | Inég $c+a$ non resp. | $c+a \leq b$, ce qui rend impossible la construction d'un triangle.                                                          | 0      |
| E2       | Triangle scalène     | $a \neq b \neq c$, ce qui correspond à un triangle avec trois côtés de longueurs différentes.                                | 1      |
| E3.1c    | Isocèle $a=b\neq c$  | $a = b \neq c$, ce qui correspond à un triangle avec deux côtés de même longueur et un côté de longueur différente.          | 2      |
| E3.2a    | Isocèle $a=c\neq b$  | $a = c \neq b$, ce qui correspond à un triangle avec deux côtés de même longueur et un côté de longueur différente.          | 2      |
| E3.3b    | Isocèle $b=c\neq a$  | $b = c \neq a$, ce qui correspond à un triangle avec deux côtés de même longueur et un côté de longueur différente.          | 2      |
| E4       | Triangle équilatéral | $a = b = c$, ce qui correspond à un triangle avec trois côtés de même longueur.                                              | 3      |

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

|                        |                                                             |                                                                 |
|-                       |-                                                            |-                                                                |
| Valeur de $a$          | $a > 0$ (CV1)                                               | $a \leq 0$ (CI1)                                                |
| Valeur de $b$          | $b > 0$ (CV2)                                               | $b \leq 0$ (CI2)                                                |
| Valeur de $c$          | $c > 0$ (CV3)                                               | $c \leq 0$ (CI3)                                                |
| Inégalité triangulaire | $\begin{cases}a+b > c\\ a+c > b\\ b+c > a\end{cases}$ (CV4) | $\neg\begin{cases}a+b > c\\ a+c > b\\ b+c > a\end{cases}$ (CI4) |

### 2.2 - Valeurs aux limites

| ID   | Description                             | $a$ | $b$ | $c$ | Retour attendu |
|-     |-                                        |-    |-    |-    |-               |
| VL1  | Borne inf. invalide de $a$              | 0   | 2   | 2   | 0              |
| VL2  | Borne inf. valide de $a$                | 1   | 2   | 2   | 2              |
| VL3  | Juste au-dessus de la borne inf. de $a$ | 2   | 2   | 2   | 3              |
| VL4  | Borne inf. invalide de $b$              | 2   | 0   | 2   | 0              |
| VL5  | Borne inf. valide de $b$                | 2   | 1   | 2   | 2              |
| VL6  | Juste au-dessus de la borne inf. de $b$ | 2   | 2   | 2   | 3              |
| VL7  | Borne inf. invalide de $c$              | 2   | 2   | 0   | 0              |
| VL8  | Borne inf. valide de $c$                | 2   | 2   | 1   | 2              |
| VL9  | Juste au-dessus de la borne inf. de $c$ | 2   | 2   | 2   | 3              |
| VL10 | Inég. $a+b$ non respectée               | 2   | 2   | 5   | 0              |
| VL11 | Inég. $a+b$ respectée                   | 3   | 3   | 5   | 2              |
| VL12 | Inég. $b+c$ non respectée               | 5   | 2   | 2   | 0              |
| VL13 | Inég. $b+c$ respectée                   | 5   | 3   | 3   | 2              |
| VL14 | Inég. $c+a$ non respectée               | 2   | 5   | 2   | 0              |
| VL15 | Inég. $c+a$ respectée                   | 3   | 5   | 3   | 2              |

## Question 3 - Couverture

