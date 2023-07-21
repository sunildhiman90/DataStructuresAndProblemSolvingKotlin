package datastructures.graph

import java.util.*

class GraphDFS(
    private var numberOfVertices: Int,
    private var adjacencyList: Array<LinkedList<Int>> = Array(numberOfVertices) {
        LinkedList()
    }
) {

    fun addEdge(from: Int, to: Int) {
        adjacencyList[from].add(to)
        adjacencyList[to].add(from)
    }

    // handle disconnected graph case as well, in that case all nodes will not be traversed using current algo
    fun dfsTraversal(startVertex: Int) {
        val visited: Array<Boolean> = Array(numberOfVertices) { false }
        dfsHelper(startVertex, visited)
    }

    // For disconnected graph support
    fun dfsTraversal2(startVertex: Int) {
        val visited: Array<Boolean> = Array(numberOfVertices) { false }
        dfsHelper(startVertex, visited)

        //check if still some vertex is remaining
        for (i in visited.indices) {
            if (!visited[i]) {
                dfsHelper(i, visited)
            }
        }
    }

    private fun dfsHelper(currentVertex: Int, visited: Array<Boolean>) {
        visited[currentVertex] = true
        println(currentVertex)
        for (element in adjacencyList[currentVertex]) {
            if (!visited[element]) {
                dfsHelper(element, visited)
            } //else part is recursion base condition here if adjacencyList has some elements
        }
    }
}

fun main() {

    println("---Connected graph--- ")
    //connected graph
    val graph = GraphDFS(6)
    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(0, 5)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)
    graph.dfsTraversal(0) //0,1,3,2,4,5


    println("---Disconnected graph--- ")

    val disconnectedGraph = GraphDFS(8)
    disconnectedGraph.addEdge(0, 1)
    disconnectedGraph.addEdge(0, 2)
    disconnectedGraph.addEdge(0, 5)
    disconnectedGraph.addEdge(1, 3)
    disconnectedGraph.addEdge(2, 4)
    disconnectedGraph.addEdge(6, 7)
    disconnectedGraph.dfsTraversal2(0) //0,1,3,2,4,5
}

/**
 * Theory:
 * The idea of the depth-first search algorithm is to start from an arbitrary node of the graph
 * and to explore as far as possible before coming back i.e. moving to an adjacent node until there is no unvisited adjacent node.
 * Then backtrack and check for other unvisited nodes and repeat the same process for them.
 * Here backtracking means, "When all the neighbors of a vertex (say x) are visited, the algorithm ends for vertex x
 * so we return and check neighbors of the vertex that initiated the call to vertex x"
 */

/**
 * Complexity Analysis of DFS Algorithm
Time complexity: Since we consider every vertex exactly once and every edge atmost twice(once while backtracking), therefore time complexity is
O(V+2E)≃O(V+E), where
V is the number of vertices and
E is the number of edges in the given graph.

Space Complexity:
O(V), because we need an auxiliary visited array/set of size V
 *
 * */