package problemsolving.graph


//We cannot use same approach of checking visited and currNeighbor != parent here as we used in undirected graph
// becoz check reason here: https://stackoverflow.com/questions/10972028/finding-a-cycle-in-an-undirected-graph-vs-finding-one-in-a-directed-graph
fun detectCycleInDirectedGraph(graph: Array<IntArray>): Boolean {

    if (graph.isEmpty()) return false

    val visited = BooleanArray(graph.size) { false }
    val recursionStack = BooleanArray(graph.size) { false }

    //in case if its disconnected component, check for all vertices which are not visited
    for (i in visited.indices) {
        if (!visited[i]) {
            val isCycle = helper(ind = i, graph, visited, recursionStack)
            if (isCycle) {
                return true //break and return true
            }
        }
    }

    return false
}

fun helper(
    ind: Int,
    graph: Array<IntArray>,
    visited: BooleanArray,
    recursionStack: BooleanArray
): Boolean {

    visited[ind] = true
    recursionStack[ind] = true

    // check neighbors of ind
    for (i in graph[ind].indices) {
        val currentNeighbor = graph[ind][i]

        if (recursionStack[currentNeighbor]) {
            return true //cycle exists
        } else if (!visited[currentNeighbor]) {
            //try with current neighbor's neighbors, only return if true, otherwise we need to check for other neighbors if false
            if (helper(currentNeighbor, graph, visited, recursionStack)) return true
        }
    }

    //here recursive stack of ind will be done, so undo here, it means no cycle in its neighbors
    recursionStack[ind] = false

    return false

}

fun main() {

    //it has cycle:  1->3->2->1

    val graph = arrayOf(
        intArrayOf(1, 2), //0
        intArrayOf(3), //1
        intArrayOf(3, 1), //2
        intArrayOf(2) //3
    )


    //it does not have cycle

    val graph2 = arrayOf(
        intArrayOf(1, 2), //0
        intArrayOf(3), //1
        intArrayOf(3), //2
        intArrayOf() //3
    )



    println(detectCycleInDirectedGraph(graph))
    println(detectCycleInDirectedGraph(graph2))


}