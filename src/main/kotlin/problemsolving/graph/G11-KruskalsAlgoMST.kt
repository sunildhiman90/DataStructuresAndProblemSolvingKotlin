package problemsolving.graph

import datastructures.graph.Edge
import datastructures.graph.Graph


// Sort Edges by weight, and apply disjoint set logic for checking if src to dest are in same component using findPair of disjoint set
// if yes do nothing, else add edge using unionByRank
fun kruskalsAlgo(graph: Graph, n: Int): List<Edge> {
    val output = mutableListOf<Edge>()

    val inputEdges = mutableListOf<Edge>()

    //Step1: create list of all edges
    for (i in 0 until n) {
        for (edge in graph.adjacencyList[i]) {
            inputEdges.add(edge)
        }
    }

    //Step2: sort according to weight
    inputEdges.sortedWith(Comparator { o1, o2 -> o1.weight - o2.weight }) //comparator from java perspective
    //edges.sortBy { it.weight } //kotlin alternative

    //Step3 apply disjoint set logic
    val ds = DisjointSet(n)
    for (edge in inputEdges) {
        val u = edge.src
        val v = edge.dest

        //check if u and v are in same component
        if (ds.findPar(u) != ds.findPar(v)) {

            output.add(edge)

            // include edge
            ds.unionByRank(u, v)

        }
    }

    return output

}

fun main() {

    val graph = createUndirectedGraphForTestingMST()

    val result = kruskalsAlgo(graph, graph.numberOfVertices)
    println("---MST edges After using Kruskals Algo--- ")
    result.forEach {
        println("${it.src} --> ${it.dest}: ${it.weight}")
    }
    println("Total MST cost: ${result.sumOf { it.weight }}")
}
