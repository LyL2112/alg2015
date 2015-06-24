#Algoritmo de Bob
largest_group=0
for  each assignment of (0,1) to the genes:
    if assignment is valid:
        group_size=number of "1"s in assignment
        largest_group=max(largest_group,group_size)
