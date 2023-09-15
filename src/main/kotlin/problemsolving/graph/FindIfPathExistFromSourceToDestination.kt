package problemsolving.graph

import java.util.*

// https://leetcode.com/problems/find-if-path-exists-in-graph/
fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    //create graph from edges
    val graph = Array<LinkedList<Int>>(n) {
        LinkedList()
    }

    val visited = BooleanArray(n) { false }

    for (edge in edges) {
        val u = edge[0]
        val v = edge[1]
        graph[u].add(v)
        graph[v].add(u)
    }

    return helperDfs(graph, source, destination, visited)

}

fun helperDfs(graph: Array<LinkedList<Int>>, source: Int, destination: Int, visited: BooleanArray): Boolean {

    if (source == destination) return true

    visited[source] = true

    for (neighbor in graph[source]) {

        if (!visited[neighbor] && helperDfs(graph, neighbor, destination, visited)) {
            if (helperDfs(graph, neighbor, destination, visited)) {
                return true
            }
        }

        // IF we use this approach, then it will not work becoz then if suppose one node has multiple childrens,
        // then suppose for first children it found false and we cant traverse ahead any more from that child,
        // then it will immediately return false from loop(that will be breaking the loop) without checking in all remaining childrens
        // try it practically, we will understand better
        /*if(!visited[neighbor]) {
            return helperDfs(graph, neighbor, destination, visited)
        }*/

    }

    return false

}