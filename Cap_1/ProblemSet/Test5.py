# Write a function inverse_graph which takes as its input the
# adjacency matrix of a graph and returns the adjacency matrix
# of the inverse graph.
from numpy import matrix
from numpy import linalg

def inverse_graph(graph):
    n=len(graph)
    inversa=[]
    for i in xrange(0,n):
        inversa.append([])
        for j in xrange(0,n):
            if i!=j:
                inversa[i].append(1-graph[i][j])
            else:
                inversa[i].append(0)
    return inversa

    print(graph)
    A = matrix(graph)
    print A.I


    # YOUR CODE HERE

def test():
    g1 = [[0, 1, 1, 0],
          [1, 0, 0, 1],
          [1, 0, 0, 1],
          [0, 1, 1, 0]]
    assert inverse_graph(g1) == [[0, 0, 0, 1],
                                 [0, 0, 1, 0],
                                 [0, 1, 0, 0],
                                 [1, 0, 0, 0]]
    g2 = [[0, 1, 1, 1],
          [1, 0, 1, 1],
          [1, 1, 0, 1],
          [1, 1, 1, 0]]
    assert inverse_graph(g2) == [[0, 0, 0, 0],
                                 [0, 0, 0, 0],
                                 [0, 0, 0, 0],
                                 [0, 0, 0, 0]]

# If you've not seen testing like this before, all you need to do is
# to call test(). If the tests pass, you'll get no output. If they don't
# you'll get an assertion error. Don't forget to remove the call to the
# test before submitting your code.

test()