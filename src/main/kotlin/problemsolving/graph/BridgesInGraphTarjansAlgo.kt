package problemsolving.graph

import datastructures.graph.Edge
import datastructures.graph.Graph

// applies to undirected graphs as well
fun bridgesInGraphTarjansAlgo(graph: Graph, n: Int): List<Edge> {
    //discovery time
    val dt = IntArray(n)
    val lowestDt = IntArray(n)
    val time = 0
    val visited = BooleanArray(n) { false }

    val output = mutableListOf<Edge>()

    for (i in 0 until n) {
        if (!visited[i]) {
            dfsForBridges(
                graph = graph,
                curr = i,
                visited = visited,
                dt = dt,
                lowestDt = lowestDt,
                time = time,
                parent = -1,
                output = output,
            )
        }
    }
    return output
}

fun dfsForBridges(
    graph: Graph,
    curr: Int,
    visited: BooleanArray,
    dt: IntArray,
    lowestDt: IntArray,
    time: Int,
    parent: Int,
    output: MutableList<Edge>
) {
    var time = time

    visited[curr] = true
    time++
    dt[curr] = time
    lowestDt[curr] = time

    for (edge in graph.adjacencyList[curr]) {

        if (edge.dest == parent) {
            continue
        } else if (!visited[edge.dest]) {
            // If not visited, first perform dfs on it, and then update lowestDt and check for bridge
            dfsForBridges(graph, edge.dest, visited, dt, lowestDt, time, curr, output)

            // update its lowestDt, to the lowest from all its neighbors
            lowestDt[curr] = Math.min(lowestDt[curr], lowestDt[edge.dest])

            //check for bridge
            if (dt[curr] < lowestDt[edge.dest]) {
                //add bridge to list
                output.add(edge)
                println("Bridge: ${edge.src} --- ${edge.dest}")
            }

        } else {
            // if visited, it means no bridge for this edge,
            // becoz if there is bridge, then there will be only one edge to current neighbor in whole graph
            lowestDt[curr] = Math.min(lowestDt[curr], dt[edge.dest])
        }
    }

}

fun createConnectedGraphForBridges2(): Graph {
    val graph = Graph(5)

    //first one is edge, second one is reverse edge, becoz we are using undirected graph
    graph.addEdge(0, 3)
    graph.addEdge(3, 0)

    graph.addEdge(0, 2)
    graph.addEdge(2, 0)

    graph.addEdge(1, 0)
    graph.addEdge(0, 1)

    graph.addEdge(2, 1)
    graph.addEdge(1, 2)

    graph.addEdge(3, 4)
    graph.addEdge(4, 3)
    return graph
}

fun createConnectedGraphForBridges(): Graph {
    val graph = Graph(6)

    //first one is edge, second one is reverse edge, becoz we are using undirected graph
    graph.addEdge(0, 3)
    graph.addEdge(3, 0)

    graph.addEdge(0, 2)
    graph.addEdge(2, 0)

    graph.addEdge(1, 0)
    graph.addEdge(0, 1)

    graph.addEdge(2, 1)
    graph.addEdge(1, 2)

    graph.addEdge(3, 4)
    graph.addEdge(4, 3)

    graph.addEdge(3, 5)
    graph.addEdge(5, 3)

    graph.addEdge(4, 5)
    graph.addEdge(5, 4)
    return graph
}


fun main() {

    val graph = createConnectedGraphForBridges()

    println(bridgesInGraphTarjansAlgo(graph, graph.numberOfVertices))


}