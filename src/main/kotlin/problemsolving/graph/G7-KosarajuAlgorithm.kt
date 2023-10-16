package problemsolving.graph

import datastructures.graph.Edge
import datastructures.graph.Graph
import java.util.*

// Find strongly connected components in directed graph only, Uses reverse graph dfs
// O(V+E)
fun kosarajuAlgo(graph: Graph, n: Int): List<List<Int>> {

    // Step 1: Perform Topological sorting
    val visited = BooleanArray(n) { false }
    val stack = Stack<Int>()

    //in case if its disconnected component, check for all vertices which are not visited
    for (i in visited.indices) {
        if (!visited[i]) {
            topoSort(curr = i, graph, visited, stack)
        }
    }

    //Step 2: Transpose the graph
    val transpose = Graph(n)
    for (i in 0 until n) {
        for (edge in graph.adjacencyList[i]) {
            // reverse the edge
            transpose.adjacencyList[edge.dest].add(Edge(edge.dest, edge.src))
        }
    }


    // Step 3: do dfs using stack nodes on transpose graph
    val output = mutableListOf<List<Int>>()
    val visited2 = BooleanArray(n) { false }
    while (!stack.isEmpty()) {
        val component = mutableListOf<Int>()
        val top = stack.pop()
        if (!visited2[top]) {
            dfs(graph = transpose, curr = top, visited2, output = component)
            output.add(component)
            println()
        }
    }

    return output.toList()

}

fun dfs(graph: Graph, curr: Int, visited: BooleanArray, output: MutableList<Int>) {

    visited[curr] = true
    print("$curr ")
    output.add(curr)

    for (edge in graph.adjacencyList[curr]) {
        if (!visited[edge.dest]) {
            dfs(graph, edge.dest, visited, output)
        }
    }
}

fun topoSort(curr: Int, graph: Graph, visited: BooleanArray, stack: Stack<Int>) {

    visited[curr] = true

    for (edge in graph.adjacencyList[curr]) {
        if (!visited[edge.dest]) {
            topoSort(edge.dest, graph, visited, stack)
        }
    }

    stack.push(curr)

}

fun createConnectedGraphForKosaraju(): Graph {
    val graph = Graph(5)
    graph.addEdge(0, 3)
    graph.addEdge(0, 2)
    graph.addEdge(1, 0)
    graph.addEdge(2, 1)
    graph.addEdge(3, 4)
    return graph
}


fun main() {
    val graph = createConnectedGraphForKosaraju()
    // this graph has 3 SCC:- [[0, 1, 2], [3], [4]]

    val result = kosarajuAlgo(graph = graph, n = graph.numberOfVertices)
    println(result)

}