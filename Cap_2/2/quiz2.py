#Input: Un Grafo con n Vertices
#       Un Entero k
#Output: Yes, si el tamaño de la asignación es mayor de  k
#        No en otro caso.

for each vertex v in G:
    if if_better:
        assign "1" to v
    else:
        assign "0" to v
if assignment is valid:
    if size of assignment is most k:
        return "Yes"
return "No"

