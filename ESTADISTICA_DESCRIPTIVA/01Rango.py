#vamos a crear un programa que pregunte al usuario por las ventas de un rango de años 
# y muestre por pantalla una serie con los datos de las ventas indexadas por los años 
# antes y después de aplicar un descuento de l10%

import pandas as pd

inicio = int(input('Introduce el año inicial de ventas: '))
fin  = int(input(('Introduce el año final de ventas:')))
ventas={}

for i in (inicio, fin+1):
    ventas[i]=float(input('introduce las ventas del año' + str(i) + ':'))
    
ventas=pd.Series(ventas)
print('ventas \n',ventas)
print('ventas con descuento \n', ventas*0.9)