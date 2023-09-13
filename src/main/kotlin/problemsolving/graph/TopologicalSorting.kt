package problemsolving.graph

import datastructures.graph.Graph
import datastructures.graph.createConnectedGraphForTesting
import java.util.*


// It only applies to DAG-> Directed Acyclic Graph
fun topologicalSorting(graph: Graph): List<Int> {

    if (graph.numberOfVertices == 0) return emptyList()

    val visited = BooleanArray(graph.numberOfVertices) { false }
    val stack = Stack<Int>()

    //in case if its disconnected component, check for all vertices which are not visited
    for (i in visited.indices) {
        if (!visited[i]) {
            helperTopo(ind = i, graph, visited, stack)
        }
    }

    val mutableList = mutableListOf<Int>()
    while (!stack.isEmpty()) {
        mutableList.add(stack.pop())
    }

    return mutableList.toList()
}

fun helperTopo(
    ind: Int,
    graph: Graph,
    visited: BooleanArray,
    stack: Stack<Int>
) {

    visited[ind] = true

    // check neighbors of current ind
    for (currentNeighbor in graph.adjacencyList[ind]) {
        if (!visited[currentNeighbor.dest]) {
            //try with current neighbor's neighbors
            helperTopo(currentNeighbor.dest, graph, visited, stack)
        }
    }

    //first traverse all neighbors and then push current ind to stack, so that when we will pop
    // from stack, for that element all its neighbors will be below that
    // stack top will be that element, to which no directional path will exist
    stack.push(ind)
    // we will reach here when we reach to a node from which we cant traverse ahead.

}


// It only applies to DAG-> Directed Acyclic Graph
fun topologicalSortingUsingArrayInput(graph: Array<IntArray>): List<Int> {

    if (graph.isEmpty()) return emptyList()

    val visited = BooleanArray(graph.size) { false }
    val stack = Stack<Int>()

    //in case if its disconnected component, check for all vertices which are not visited
    for (i in visited.indices) {
        if (!visited[i]) {
            helperTopo2(ind = i, graph, visited, stack)
        }
    }

    val mutableList = mutableListOf<Int>()
    while (!stack.isEmpty()) {
        mutableList.add(stack.pop())
    }

    return mutableList.toList()
}

fun helperTopo2(
    ind: Int,
    graph: Array<IntArray>,
    visited: BooleanArray,
    stack: Stack<Int>
) {

    visited[ind] = true

    // check neighbors of current ind
    for (i in graph[ind].indices) {
        val currentNeighbor = graph[ind][i]
        if (!visited[currentNeighbor]) {
            //try with current neighbor's neighbors
            helperTopo2(currentNeighbor, graph, visited, stack)
        }
    }

    //first traverse all neighbors and then push current ind to stack, so that when we will pop
    // from stack, for that element all its neighbors will be below that
    // stack top will be that element, to which no directional path will exist
    stack.push(ind)

}

fun main() {

    //it does not have cycle

    val graph2 = arrayOf(
        intArrayOf(1, 2), //0
        intArrayOf(3), //1
        intArrayOf(3), //2
        intArrayOf() //3
    )

    println(topologicalSortingUsingArrayInput(graph2))


    val graph = createConnectedGraphForTesting()
    println(topologicalSorting(graph))

}

/**
 * EXPLANATION:-
 *
 * When we are scheduling jobs or tasks, they may have dependencies.
 * For example, before we finish task a, we have to finish b first.
 * In this case, given a set of tasks and their dependencies,
 * how shall we arrange our schedules?
 * There comes an interesting graph algorithm:
 * Topological Sort.
 *
 * According to Introduction to Algorithms, given a directed acyclic graph (DAG),
 * a topological sort is a linear ordering of all vertices such that for any edge (u, v),
 * u comes before v. Another way to describe it is that when you put all vertices horizontally
 * on a line, all of the edges are pointing from left to right.
 *
 * Applications:-
 * https://iq.opengenus.org/applications-of-topological-sort/
 *
 *
 */


/**
 * Time Complexity: O(V+E)
 */