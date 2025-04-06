#Calcularemos las distancias entre todos los pares de puntos y determinaremos 
#cuáles están más alejados entre sí y cuáles están más cercanos, 
# utilizando las distancias Euclidiana, Manhattan y Chebyshev.
#Ejercicio: Determinación de Distancias entre Puntos
#Puntos en el Plano

#Los puntos en el plano son los siguientes:

#    Punto A: (2, 3)
#   Punto B: (5, 4)
#    Punto C: (1, 1)
#    Punto D: (6, 7)
#    Punto E: (3, 5)
#    Punto F: (8, 2)
#    Punto G: (4, 6)
#    Punto H: (2, 1)

import numpy as np
import pandas as pd
from scipy.spatial import distance

# Definimos los puntos
puntos = {
    'Punto A': (2, 3),
    'Punto B': (5, 4),
    'Punto C': (1, 1),
    'Punto D': (6, 7),
    'Punto E': (3, 5),
    'Punto F': (8, 2),
    'Punto G': (4, 6),
    'Punto H': (2, 1)
}

df_pts = pd.DataFrame(puntos).T
df_pts.columns = ['X', 'Y']

# Creamos las matrices de distancias
eucl = pd.DataFrame(index=df_pts.index, columns=df_pts.index)
manh = pd.DataFrame(index=df_pts.index, columns=df_pts.index)
cheb = pd.DataFrame(index=df_pts.index, columns=df_pts.index)

# Calculamos las distancias
for i in df_pts.index:
    for j in df_pts.index:
        eucl.loc[i, j] = distance.euclidean(df_pts.loc[i], df_pts.loc[j])
        manh.loc[i, j] = distance.cityblock(df_pts.loc[i], df_pts.loc[j])
        cheb.loc[i, j] = distance.chebyshev(df_pts.loc[i], df_pts.loc[j])

print('\nDistancia Euclidiana entre cada par de puntos:')
print(eucl)

print('\nDistancia Manhattan entre cada par de puntos:')
print(manh)

print('\nDistancia Chebyshev entre cada par de puntos:')
print(cheb)

# Encontramos el par más cercano y más lejano (sin comparar el mismo punto)
def find_extremos(df, tipo):
    min_val = np.inf
    max_val = -np.inf
    min_pair = ()
    max_pair = ()

    for i in df.index:
        for j in df.columns:
            if i != j:  # No comparar un punto consigo mismo
                val = float(df.loc[i, j])
                if val < min_val:
                    min_val = val
                    min_pair = (i, j)
                if val > max_val:
                    max_val = val
                    max_pair = (i, j)

    print(f"\n[{tipo}] Punto más CERCANO: {min_pair} con distancia {min_val:.2f}")
    print(f"[{tipo}] Punto más ALEJADO: {max_pair} con distancia {max_val:.2f}")

# Buscar extremos para cada tipo de distancia
find_extremos(eucl, 'Euclidiana')
find_extremos(manh, 'Manhattan')
find_extremos(cheb, 'Chebyshev')
