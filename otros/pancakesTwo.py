from itertools import *

def flip(stack,x,y):
	return stack[0:len(stack)-y] + stack[len(stack)-y:len(stack)-x][::-1] + stack[len(stack)-x:]


def flip_sequence(stack, flips):
    flipped = stack
    for i in xrange(len(flips)):
        flipped = flip(flipped, flips[i][0], flips[i][1])
    return flipped

def sequence_space(length):
    n = length
    flip_pairs = [(a, b) for a in xrange(0, n - 1) for b in xrange(a + 2, n + 1)]
    iterators = (product(flip_pairs, repeat=i) for i in xrange(1, length + 1))
    return filter(non_redundant_flips, chain(*iterators))

def is_sorted(stack):
    return all(stack[i] < stack[i + 1] for i in xrange(len(stack) - 1))

def non_redundant_flips(sequence):
    return not any(sequence[i] == sequence[i + 1] for i in xrange(len(sequence) - 1)) or len(sequence) == 1

max_stack_size = int(raw_input("Por Favor el Tamaño de la Pila "))
#max_stack_size = 7
pn= []
permn=[]
it=1
for perm in permutations(xrange(1, max_stack_size + 1)):
   find=False
   i=0
   valid_sequences = []
   if is_sorted(perm):
      valid_sequences=[]
      it=it+1
      print  "La Iteracion %s Con la Permutacion : %s" % (it-1,perm)
      print "La Lista de Pancakes %s pancakes no requiere de ningun flip para ser ordenado " %(max_stack_size)
      print ' '    
   else:
      while(find==False):
         for flips in sequence_space(max_stack_size+i):
        
            result = flip_sequence(perm, flips)
            if is_sorted(result):
               find=True
               if len(valid_sequences)==0:
                  valid_sequences.append(flips)
               elif len(valid_sequences[0])==len(flips):
                  valid_sequences.append(flips)                  
         if valid_sequences == []:
            i=i+1
         else:
            print  "La Iteracion %s Con la Permutacion : %s" % (it,perm)
            it=it+1
            print "La Lista de Pancakes %s pancakes puede ser ordenada minimo en %s flips para la siguiente secuencia de flips:"  %(max_stack_size,len(valid_sequences[0]))
            print valid_sequences            
            print ' '
            if (pn==[]):
               pn.append(valid_sequences[0])
               permn.append(perm)
            if (len(pn[0]) < len(valid_sequences[0])):
               pn= []
               permn=[]
               pn.append(valid_sequences[0])
               permn.append(perm)
            elif (len(pn[0]) == len(valid_sequences[0]) and permn[len(permn)-1]!=perm):
               pn.append(valid_sequences[0])
               permn.append(perm)


print "El  P_n es  %s" %(len(pn[0]))
print "El numero de  Pn  %s" %(len(permn))
for i in range(0,len(pn)):   
   print "Las Permutaciones  %s son ordenasdas lo minimo posible por la siguiente secuencia %s" %(permn[i],pn[i])