package problemsolving.graph


fun detectCycleInUndirectedGraph(graph: Array<IntArray>): Boolean {

    if (graph.isEmpty()) return false

    val visited = BooleanArray(graph.size) { false }

    //in case if its disconnected component, check for all vertices which are not visited
    for (i in visited.indices) {
        if (!visited[i]) {
            val isCycle = helperUndirected(ind = i, graph, visited, -1)
            if (isCycle) {
                return true //break and return true
            }
        }
    }

    return false
}

fun helperUndirected(
    ind: Int,
    graph: Array<IntArray>,
    visited: BooleanArray,
    parent: Int
): Boolean {

    visited[ind] = true

    // check neighbors of ind
    for (i in graph[ind].indices) {
        val currentNeighbor = graph[ind][i]

        if (visited[currentNeighbor] && currentNeighbor != parent) {
            return true //cycle exists
        } else if (!visited[currentNeighbor]) {
            //try with current neighbor's neighbors
            return helperUndirected(currentNeighbor, graph, visited, ind)
        }
    }

    return false

}

fun main() {

    //it has cycle:  0->1->3->2->0

    val graph = arrayOf(
        intArrayOf(1), //0
        intArrayOf(0, 3), //1
        intArrayOf(1, 3), //2
        intArrayOf(1, 2) //3
    )


    //it does not have cycle: 0->1, 1->3, 1->0, 2->3, 3->1,2

    val graph2 = arrayOf(
        intArrayOf(1), //0
        intArrayOf(0, 3), //1
        intArrayOf(3), //2
        intArrayOf(1, 2)
    )

    println(detectCycleInUndirectedGraph(graph))
    println(detectCycleInUndirectedGraph(graph2))


}