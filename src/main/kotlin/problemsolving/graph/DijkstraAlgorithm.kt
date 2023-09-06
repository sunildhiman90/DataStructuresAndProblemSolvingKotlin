package problemsolving.graph

import datastructures.graph.Graph
import java.util.*

class DijkstraAlgorithm {
}


// We are using PQ, becoz its first element always become smallest after we add any node , so for smallest distacne we are using this
// for using in PQ-> node and its distance, make it comparable so that  PQ will be able to compare it by dist
// node:- current node
// dist:- current node distance from source
class DijPair(val node: Int, private val dist: Int) : Comparable<DijPair> {
    override fun compareTo(other: DijPair) = this.dist - other.dist

}


// O(E + E.log(V)):- E.log(v) is becoz of Priority Queue sorting
fun dijkstraAlgo(graph: Graph, src: Int, n: Int): IntArray {
    val pq = PriorityQueue<DijPair>()
    pq.add(DijPair(0, 0))

    //except source src, use max for all vertices
    val dist = IntArray(n) {
        if (it == src) src else Int.MAX_VALUE
    }

    val visited = BooleanArray(n) {
        false
    }

    while (!pq.isEmpty()) {

        val curr = pq.remove()
        if (!visited[curr.node]) {
            visited[curr.node] = true

            //traverse all neighbors of curr and calculate shortest distance from curr
            for (edge in graph.adjacencyList[curr.node]) {
                val u = edge.src
                val v = edge.dest

                //relaxation step
                if (dist[u] + edge.weight < dist[v]) {
                    dist[v] = dist[u] + edge.weight
                    pq.add(DijPair(v, dist[v]))

                }
            }
        }

    }

    return dist

}


fun createConnGraphForTestingWithWeight(): Graph {
    val graph = Graph(6)
    graph.addEdge(0, 1, 2)
    graph.addEdge(0, 2, 4)
    graph.addEdge(1, 2, 1)
    graph.addEdge(1, 3, 7)
    graph.addEdge(2, 4, 3)
    graph.addEdge(3, 5, 1)
    graph.addEdge(4, 3, 2)
    graph.addEdge(4, 5, 5)
    return graph
}

fun main() {

    println("---Connected graph--- ")

    val graph = createConnGraphForTestingWithWeight()

    val result = dijkstraAlgo(graph, 0, graph.numberOfVertices)
    println(result.contentToString())
}