import pandas as pd

#crear una fuuncion que se encargue de esccrivbir un duiccionario 
# de las notas de los estudiantes de analisis de datos que  van a reprobar y obtener su min, max, 
# media y desviacion estandar

def estadisticaNotas(notas):
    notas = pd.Series(notas)
    estadistica=pd.Series([notas.min(), notas.max(),notas.mean(),notas.std()], index=['Min', 'Max', 'Media','Desviacion Estandar'])
    return estadistica

def aprobados(notas):
    notas=pd.Series(notas)
    return notas[notas>=6].sort_values(ascending=False)

notas = {'Juan':9,'Juanita':5.9,'Pedro':8.2,'Rosalba':6.9,'Federico':4.5,'Alberto':7.5}

print(estadisticaNotas(notas))
print('Aprobados',aprobados(notas))