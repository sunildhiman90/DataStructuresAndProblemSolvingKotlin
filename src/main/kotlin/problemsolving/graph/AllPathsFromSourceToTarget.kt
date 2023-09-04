package problemsolving.graph


//https://leetcode.com/problems/all-paths-from-source-to-target/
class AllPathsFromSourceToTarget {
}


fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
    return allPathsSourceTarget(graph, source = 0, target = graph.size - 1)
}

fun allPathsSourceTarget(graph: Array<IntArray>, source: Int, target: Int): List<List<Int>> {
    val output = mutableListOf<List<Int>>()

    if (graph.isEmpty()) output.toList()

    val visited = BooleanArray(graph.size) { false }
    val currentList = mutableListOf<Int>()

    // add source , we will start from it
    currentList.add(source)
    visited[source] = true

    helper(source, target, currentList, graph, output, visited)

    return output.toList()

}

fun helper(
    source: Int,
    target: Int,
    currentList: MutableList<Int>,
    graph: Array<IntArray>,
    output: MutableList<List<Int>>,
    visited: BooleanArray
) {
    if (source == target) {
        output.add(currentList.toList())
        return
    }

    // check neighbors of ind
    for (i in graph[source].indices) {
        val currentNeighbor = graph[source][i]

        if (visited[currentNeighbor]) break

        // mark ind visited and add current node(ith neighbor of node at ind index)
        visited[currentNeighbor] = true
        currentList.add(currentNeighbor)

        //try with current neighbor's neighbors
        helper(currentNeighbor, target, currentList, graph, output, visited)

        //undo current node
        visited[currentNeighbor] = false
        currentList.removeAt(currentList.size - 1)


    }

}

fun main() {

    val graph = arrayOf(
        intArrayOf(4, 3, 1),
        intArrayOf(3, 2, 4),
        intArrayOf(3),
        intArrayOf(4),
        intArrayOf()
    )

    println(allPathsSourceTarget(graph))


}