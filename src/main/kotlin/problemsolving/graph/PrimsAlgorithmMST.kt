package problemsolving.graph

import datastructures.graph.Edge
import datastructures.graph.Graph
import java.util.*


// We are using PQ, becoz its first element always become smallest after we add any node , so for smallest edge distacne we are using this
// for using in PQ-> node and its distance, make it comparable so that  PQ will be able to compare it by dist
// src:- source node
// node:- current node
// dist:- distance from src to node
class PrimPair(val src: Int, val node: Int, val weight: Int) : Comparable<PrimPair> {
    override fun compareTo(other: PrimPair) = this.weight - other.weight

}


// O(E.log(E)):- E.log(E) is becoz of Priority Queue sorting while adding
fun primsAlgo(graph: Graph, src: Int, n: Int): List<Edge> {

    // MST set
    val pq = PriorityQueue<PrimPair>()
    pq.add(PrimPair(src, src, 0))

    // for minimum spanning tree edges with costs
    val output = mutableListOf<Edge>()

    /// Non MST set
    val visited = BooleanArray(n) {
        false
    }

    while (!pq.isEmpty()) {
        // we need to check node which has shortest distance and unvisited,
        // thats why using priority queue, it will give us shortest distance node when calling remove
        val curr = pq.remove()
        if (!visited[curr.node]) {

            // WE are not setting it visited outside while loop like bfs,
            // becoz in bfs, we are making them visited inside neighbors loop, but here we are not, thats why
            visited[curr.node] = true
            output.add(Edge(curr.src, curr.node, curr.weight))

            //traverse all neighbors of curr and add them with there weight or cost to mst set pq
            for (edge in graph.adjacencyList[curr.node]) {
                val u = edge.src
                val v = edge.dest

                //main step, if node is not visited, add it with its weight or cost to mst set pq
                if (!visited[v]) {
                    pq.add(PrimPair(u, v, edge.weight))
                }

            }
        }

    }

    return output

}


fun main() {


    val graph = createUndirectedGraphForTestingMST()

    val result = primsAlgo(graph, 0, graph.numberOfVertices)
    println("---MST edges After using Prims Algo--- ")
    result.forEach {
        println("${it.src} --> ${it.dest}: ${it.weight}")
    }

    println("Total MST cost: ${result.sumOf { it.weight }}")
}

fun createUndirectedGraphForTestingMST(): Graph {
    val graph = Graph(6)
    graph.addEdge(0, 1, 10)
    graph.addEdge(1, 0, 10)

    graph.addEdge(1, 3, 40)
    graph.addEdge(3, 1, 40)

    graph.addEdge(0, 3, 30)
    graph.addEdge(3, 0, 30)

    graph.addEdge(0, 2, 15)
    graph.addEdge(2, 0, 15)

    graph.addEdge(2, 3, 50)
    graph.addEdge(3, 2, 50)

    return graph
}

/**
 * Intuition:
 * The intuition of this algorithm is the greedy technique used for every node.
 * If we carefully observe, for every node, we are greedily selecting its unvisited adjacent
 * node with the minimum edge weight(as the priority queue here is a min-heap and
 * the topmost element is the node with the minimum edge weight).
 * Doing so for every node, we can get the sum of all the edge weights of the minimum spanning tree
 * and the spanning tree itself(if we wish to) as well.
 */

/**
 * Time Complexity: O(E*logE) + O(E*logE)~ O(E*logE), where E = no. of given edges.
 *
 * The maximum size of the priority queue can be E so after at most E iterations
 * the priority queue will be empty and the loop will end. Inside the loop,
 * there is a pop operation that will take logE time. This will result in the
 * first O(E*logE) time complexity. Now, inside that loop, for every node,
 * we need to traverse all its adjacent nodes where the number of nodes can be at most E.
 * If we find any node unvisited, we will perform a push operation and for that,
 * we need a logE time complexity. So this will result in the second O(E*logE).
 *
 */