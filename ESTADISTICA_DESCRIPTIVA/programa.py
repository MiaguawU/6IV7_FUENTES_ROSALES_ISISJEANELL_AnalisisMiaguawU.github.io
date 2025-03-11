import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('6IV7_FUENTES_ROSALES_ISISJEANELL_ANALISIS\ESTADISTICA_DESCRIPTIVA\housing.csv')

#mostrar las primeras 5 filas
#print(df.head())

#Las ultimas 5 filas
#print(df.tail())

#quiero una fila en especifico
#print(df.iloc[7])

#mostrar una columna por su nombre
#print(df['ocean_proximity'])

#obtener la media de la columna de total de cuartos
mediacuartos =  df['total_rooms'].mean()
print('Media de los cuartos: ', mediacuartos)

inicio = int(input(""" Decida la estadística a calcular.
        1. Longitud
        2. Latitud
        3. Edad media de la vivienda
        4. Total de habitaciones
        5. Total de dormitorios
        6. Población
        7. Viviendas
        8. Promedio de ingresos
        9. Valor promedio de la casa
        """))

columna = ['longitude', 'latitude', 'housing_median_age']
    

#obtener la mediana 
mediana = df[columna[inicio-1]].median()
mediana2 = df['longitude'].median()
medianapopularidad = df['population'].median()
print('Mediana: ', mediana)
print('Mediana 2: ', mediana2)

std_age = df['housing_median_age'].std()
#print('Desviación Estándar de años: ', std_age)

#vamos a crear un grafico de dispersión entre los registros de la proximidad del oceano contra los precios
plt.scatter(df["ocean_proximity"][:10],df['median_house_value'][:10])

"hay que definir a x y a y"
plt.xlabel('Proximidad')
plt.ylabel('Precio')

plt.title('Gráfico de dispersión de proximidad del oceano vs precio')
plt.show()
