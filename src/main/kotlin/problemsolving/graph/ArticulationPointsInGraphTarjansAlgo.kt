package problemsolving.graph

import datastructures.graph.Graph

// applies to undirected graphs as well
// O(V+E)
fun articuPointsInGraphTarjansAlgo(graph: Graph, n: Int): List<Int> {
    //discovery time
    val dt = IntArray(n)
    val lowestDt = IntArray(n)
    val time = 0
    val visited = BooleanArray(n) { false }

    val output = mutableListOf<Int>()

    for (i in 0 until n) {
        if (!visited[i]) {
            dfsForArticuPoints(
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

fun dfsForArticuPoints(
    graph: Graph,
    curr: Int,
    visited: BooleanArray,
    dt: IntArray,
    lowestDt: IntArray,
    time: Int,
    parent: Int,
    output: MutableList<Int>
) {
    var time = time

    visited[curr] = true
    time++
    dt[curr] = time
    lowestDt[curr] = time
    var children = 0

    for (edge in graph.adjacencyList[curr]) {

        if (edge.dest == parent) {
            continue
        } else if (!visited[edge.dest]) {
            // If not visited, first perform dfs on it, and then update lowestDt and check for bridge
            dfsForArticuPoints(graph, edge.dest, visited, dt, lowestDt, time, curr, output)

            // It will come back here after finishing dfs for all its neighbors' neighbors in depth when we cant traverse any more
            // update its lowestDt, to the lowest from all its neighbors
            lowestDt[curr] = Math.min(lowestDt[curr], lowestDt[edge.dest])

            //check for AP
            if (dt[curr] <= lowestDt[edge.dest] && parent != -1) {
                //add Articulation Point(AP) node  to list
                output.add(curr)
            }


            children++

        } else {
            // if visited (backedge ancestor), it means no AP check for this node,
            lowestDt[curr] = Math.min(lowestDt[curr], dt[edge.dest])
        }
    }

    // start node and having more than 1 disconnected children
    if (parent == -1 && children > 1) {
        //add Articulation Point node  to list
        output.add(curr)
    }

}

fun createConnectedGraphForAP2(): Graph {
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

fun createConnectedGraphForAP(): Graph {
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

    val graph = createConnectedGraphForAP()

    println(articuPointsInGraphTarjansAlgo(graph, graph.numberOfVertices))


}

/**
 *
 * 2 Cases will be there for checking AP
 *
 * 1st Case:- parent of curr == -1 && disconnected children > 1 =>
 * it means start node of graph and should have more than one children which are not connected directly or indirectly except through parent connection and
 * cant be traversed from any node except parent.
 *
 * 2nd Case:- if parent of curr != -1, then in 2 conditions we can have AP
 * (i) First Condition is there is single route from curr(u) to neighbor(v):-
 *    dt(u) < lowest(v) means u should be visited before v and all its neighbors
 *
 * (ii) Second Condition is there is some cycle from u -> v :-
 *    dt(u) == lowest(v) means u and its neighbors will update their lowest with dt(u) due to visited node condition
 *    when last node will visit u again due to cycle, it will update its lowest with dt(u) and then its parent and so on
 *    till all neighbor backtrack again to u. Try it practically with dry run
 *
 *
 */