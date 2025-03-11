import pandas as pd

#vamos a hacer un ejemplo de carga de archivos y aplicar min,max, media y desviacion estandar

def cotizacion(fichero):
    df=pd.read_csv(fichero, sep=';',decimal=',',thousands='.',index_col=0)
    return pd.DataFrame([df.min(), df.max(),df.mean(),df.std()], index=['Minimo','Maximo','Media','Desviacion Estandar'])

print(cotizacion('6IV7_FUENTES_ROSALES_ISISJEANELL_ANALISIS\ESTADISTICA_DESCRIPTIVA\cotizacion.csv'))