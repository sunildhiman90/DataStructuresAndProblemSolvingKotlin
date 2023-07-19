package datastructures.graph

import java.util.*

class GraphDFS(
    var numberOfVertices: Int,
    private var adjacencyList: Array<LinkedList<Int>> = Array(numberOfVertices) {
        LinkedList()
    }
) {

    fun addEdge(from: Int, to: Int) {
        adjacencyList[from].add(to)
        adjacencyList[to].add(from)
    }

    //TODO, handle disconnected graph case as well, in that case all nodes will not be traversed using current algo

    fun dfsTraversal(startVertex: Int) {
        val visited: Array<Boolean> = Array(numberOfVertices) { false }
        dfsHelper(startVertex, visited)
    }

    private fun dfsHelper(currentVertex: Int, visited: Array<Boolean>) {
        visited[currentVertex] = true
        println(currentVertex)
        for (element in adjacencyList[currentVertex]) {
            if(!visited[element]) {
                dfsHelper(element, visited)
            }
        }
    }
}

fun main() {
    val graph = GraphDFS(6)

    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(0, 5)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)

    graph.dfsTraversal(0) //0,1,3,2,4,5
}

/**
 * Complexity Analysis of DFS Algorithm
Time complexity: Since we consider every vertex exactly once and every edge atmost twice, therefore time complexity is
O(V+2E)≃O(V+E), where
V is the number of vertices and
E is the number of edges in the given graph.

Space Complexity:
O(V), because we need an auxiliary visited array/set of size V
 *
 * */