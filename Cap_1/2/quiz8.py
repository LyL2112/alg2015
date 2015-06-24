import sys
string= sys.stdin.readline()


count=0  #0 timestep
for i in xrange(len(string)):  #n+1 timestep
    if string[i]=="a": #n timestep
        if string[i+1]=="b":#a timestep
            count+=1   #b timestep
print(count)


runningtime=2*len(string)+1*count+0
"""
Input

ababababababa...
aaaaaaaaaaaaa...
acacacacacaca...
bbbbbbbbbbbbb...

"""

