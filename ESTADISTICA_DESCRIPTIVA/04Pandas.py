import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('6IV7_FUENTES_ROSALES_ISISJEANELL_ANALISIS\ESTADISTICA_DESCRIPTIVA\housing.csv')

#mostrar las primeras 5 filas
print(df.head())

#Las ultimas 5 filas
print(df.tail())

#quiero una fila en especifico
print(df.iloc[7])

#mostrar una columna por su nombre
print(df['ocean_proximity'])

#obtener la media de la columna de total de cuartos
mediacuartos =  df['total_rooms'].mean()
print('Media de los cuartos: ', mediacuartos)

#obtener la mediana de la columna population
medianapopularidad = df['population'].median()
print('Mediana de popularidad: ', medianapopularidad)

std_age = df['housing_median_age'].std()
print('Desviación Estándar de años: ', std_age)

#para poder filtrar
filtrodeloceano = df[df['ocean_proximity'] == "ISLAND"]
print("filtro de proximidad del oceano:\n", filtrodeloceano)

#vamos a crear un grafico de dispersión entre los registros de la proximidad del oceano contra los precios
plt.scatter(df["ocean_proximity"][:10],df['median_house_value'][:10])

"hay que definir a x y a y"
plt.xlabel('Proximidad')
plt.ylabel('Precio')

plt.title('Gráfico de dispersión de proximidad del oceano vs precio')
plt.show()
