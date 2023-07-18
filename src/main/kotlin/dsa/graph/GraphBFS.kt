package dsa.graph

import java.util.*

class GraphBFS(
    var numberOfVertices: Int,
    private var adjacencyList: Array<LinkedList<Int>> = Array(numberOfVertices) {
        LinkedList()
    }
) {

    fun addEdge(from: Int, to: Int) {
        adjacencyList[from].add(to)
        adjacencyList[to].add(from)
    }

    //TODO, calculate level using BFS, BFS Traversal is also called level traversal of graph
    //TODO, handle disconnected graph case as well, in that case all nodes will not be traversed using current algo


    fun bfsTraversal(startVertex: Int) {
        val visited: Array<Boolean> = Array(numberOfVertices) { false } //instead of Boolean array, we can use Set as well
        val queue: LinkedList<Int> = LinkedList()

        visited[startVertex] = true
        queue.add(startVertex)

        while (!queue.isEmpty()) {
            val currentVertex = queue.poll()
            println("$currentVertex ")

            for (element in adjacencyList[currentVertex]) {
                if(!visited[element]) {
                    visited[element] = true
                    queue.add(element)
                }
            }
        }
    }
}

fun main() {
    val graph = GraphBFS(6)

    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(0, 5)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)

    graph.bfsTraversal(0) //0,1,2,5,3,4
}

/**
 * Complexity Analysis of BFS
 *
Time Complexity Analysis:
If we notice carefully, Breadth-First Search tries to visit one vertex at a time, until all are visited. From each vertex, we explore all of its neighbouring vertices, which is nothing but iterating all vertices connected to it on all of its edges.

So this essentially boils down to visiting each Vertex, and we have V total vertices.

From each V, we iterate all of the other neighbour vertices i.e. at the other end of all of its edges, and the total edges that we can have in the graph is E. Then it means Breadth-First Search works in O(E + V) time.

Space Complexity Analysis:
We use two data structures in Breadth-First Search - the visited array/Set, and the Queue.
A Visited array will have the size of the number of vertices in the graph (Similarly, the maximum size of a Set can also be up to the number of vertices in the graph).

Queue carries the vertices, as it explores them, and the maximum nodes it can have is again as many as the vertices in the Graph. E.g. If the starting vertex is 0 and all other vertices are connected to 0, then when we explore 0, we add all other vertices(as those are neighbours of 0) to Queue. So, the Queue will carry V-1 vertices in the worst case.

Since both the Visited array and Queue can have a max size of V(equal to as many vertices), the overall space complexity will be O(V).
 */