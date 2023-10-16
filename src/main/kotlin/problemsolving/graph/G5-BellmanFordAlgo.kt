package problemsolving.graph

import datastructures.graph.Edge
import datastructures.graph.Graph


// It works for shortest path for negative and positive both weights,
// But not for negative weight cycles

// O(V*E)
fun bellmanFordAlgo(graph: Graph, src: Int, n: Int): IntArray {

    //except source src, use max for all vertices
    val dist = IntArray(n) {
        if (it == src) src else Int.MAX_VALUE
    }

    // run outer loop n-1 times, becoz largest path between any 2 nodes in graph is of V-1 nodes
    // So doing so will explore all possible paths between nodes
    for (k in 0 until n - 1) { //O(V)

        // Traverse all edges
        //O(E)
        for (i in 0 until n) {
            for (j in 0 until graph.adjacencyList[i].size) {
                val e: Edge = graph.adjacencyList[i][j]
                val u = e.src
                val v = e.dest
                if (dist[u] != Int.MAX_VALUE && dist[u] + e.weight < dist[v]) {
                    dist[v] = dist[u] + e.weight
                }
            }
        }
    }
    return dist
}


fun bellmanFordDetectNegativeWeightCycle(graph: Graph, src: Int, n: Int): Boolean {

    //except source src, use max for all vertices
    val dist = IntArray(n) {
        if (it == src) src else Int.MAX_VALUE
    }

    // run outer loop n-1 times, becoz largest path between any 2 nodes in graph is of V-1 nodes
    // So doing so will explore all possible paths between nodes
    for (k in 0 until n - 1) { //O(V)

        // Traverse all edges
        //O(E)
        for (i in 0 until n) {
            for (j in 0 until graph.adjacencyList[i].size) {
                val e: Edge = graph.adjacencyList[i][j]
                val u = e.src
                val v = e.dest
                if (dist[u] != Int.MAX_VALUE && dist[u] + e.weight < dist[v]) {
                    dist[v] = dist[u] + e.weight
                }
            }
        }
    }


    // If even after performing loop V-1 times on all edges, still we are able to find relaxation condition true
    // it means it has negative weight cycle
    var isNegativeWeightCycle = false
    //detect -ve weight cycle
    outerloop@ for (i in 0 until n) {
        for (j in 0 until graph.adjacencyList[i].size) {
            val e: Edge = graph.adjacencyList[i][j]
            val u = e.src
            val v = e.dest
            if (dist[u] != Int.MAX_VALUE && dist[u] + e.weight < dist[v]) {
                isNegativeWeightCycle = true
                break@outerloop
            }
        }
    }

    return isNegativeWeightCycle

}

fun main() {

    println("---Connected graph--- ")

    val graph = createConnGraphForTestingWithWeight()

    val result = bellmanFordAlgo(graph, 0, graph.numberOfVertices)
    println(result.contentToString())
}


/***
 *
 * Does not work for negative weight cycles(if there is a cycle and sum of all edges weights is
 * negative in that cycle). But usually, if negative weight cycle exists in graph,
 * then we can't calculate the shortest path becoz due to that negative weight cycle,
 * every time we will perform relaxation of edges, it will keep becoming
 * smaller and smaller (-negative value) and will become negative infinity,
 * and the loop will keep going.. That's why we can't calculate the shortest path
 *
 */