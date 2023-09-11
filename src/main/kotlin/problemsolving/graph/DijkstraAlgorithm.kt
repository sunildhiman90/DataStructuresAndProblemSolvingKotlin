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


// O(E.log(V)):- E.log(v) is becoz of Priority Queue sorting while adding
fun dijkstraAlgo(graph: Graph, src: Int, n: Int): IntArray {
    val pq = PriorityQueue<DijPair>()
    pq.add(DijPair(src, src))

    //except source src, use max for all vertices, It is distance of current node from src
    val dist = IntArray(n) {
        if (it == src) src else Int.MAX_VALUE
    }

    val visited = BooleanArray(n) {
        false
    }

    while (!pq.isEmpty()) {
        // we need to check node which has shortest distance and unvisited,
        // thats why using priority queue, it will give us shortest distance node when calling remove
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

/**
 * Time Complexity Analysis
 *
 * We know from past lectures that the time complexity of Dijkstra’s Algorithm is O(E*logV) where E= number of edges and V= number of nodes.
 *
 * Now, we will see how can we reach this particular time complexity with the help of a simple derivation:
 *
 * Pseudocode of Priority Queue :
 * while loop -> V times
 * remove method in while loop -> log(heapsize)
 * innner for loop -> edges times -> V-1 for worst case if every node is connected to every other node
 *
 * Derivation:
 *
 * O( V * ( pop vertex from min heap + no. of edges on each vertex * push in PQ ))
 *
 * O( V * ( log(heapSize) + no. of edges * log(heapSize))
 *
 * O( V * (log(heapSize) + V-1 * log(heapSize))    { one vertex can have V-1 edges }
 *
 * O( V * (log(heapSize) * (V-1+1))
 *
 * O( V * V * log(heapSize))
 *
 * Now, at the worst case the heapSize will be equivalent to v² as if we consider pushing adjacent elements of a node, at the worst case each element will have V-1 nodes and they will be pushed onto the queue.
 *
 * O( V * V * log(v²))
 *
 * O ( v² * 2 log (V))
 *
 * O ( E * 2 log(V))  { E= v², total number of edges}
 *
 * O ( E * log(V))  Worst case Time Complexity of Dijkstra’s Algorithm.
 *
 *
 *
 */