import sys
string= sys.stdin.readline()


count=0  #0 timestep
for i in string:  #n+1 timestep
    if i=="a":    #n timestep
        count+=1   #a timestep
print(count)


runningtime=2*len(string)+1*count+0
#runningtime=2*n+1*+0/1
