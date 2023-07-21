package datastructures.graph

import java.util.*

class GraphDFS(
    private var numberOfVertices: Int,
    private var adjacencyList: Array<LinkedList<Int>> = Array(numberOfVertices) {
        LinkedList()
    }
) {

    fun addEdge(from: Int, to: Int) {
        adjacencyList[from].add(to)
        adjacencyList[to].add(from)
    }

    // handle disconnected graph case as well, in that case all nodes will not be traversed using current algo
    fun dfsTraversal(startVertex: Int) {
        val visited: Array<Boolean> = Array(numberOfVertices) { false }
        dfsHelper(startVertex, visited)
    }

    // For disconnected graph support
    fun dfsTraversal2(startVertex: Int) {
        val visited: Array<Boolean> = Array(numberOfVertices) { false }
        dfsHelper(startVertex, visited)

        //check if still some vertex is remaining
        for (i in visited.indices) {
            if (!visited[i]) {
                dfsHelper(i, visited)
            }
        }
    }

    private fun dfsHelper(currentVertex: Int, visited: Array<Boolean>) {
        visited[currentVertex] = true
        println(currentVertex)
        for (element in adjacencyList[currentVertex]) {
            if (!visited[element]) {
                dfsHelper(element, visited)
            } //else part is recursion base condition here if adjacencyList has some elements
        }
    }
}

fun main() {

    println("---Connected graph--- ")
    //connected graph
    val graph = GraphDFS(6)
    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(0, 5)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)
    graph.dfsTraversal(0) //0,1,3,2,4,5


    println("---Disconnected graph--- ")

    val disconnectedGraph = GraphDFS(8)
    disconnectedGraph.addEdge(0, 1)
    disconnectedGraph.addEdge(0, 2)
    disconnectedGraph.addEdge(0, 5)
    disconnectedGraph.addEdge(1, 3)
    disconnectedGraph.addEdge(2, 4)
    disconnectedGraph.addEdge(6, 7) //disconnected part
    disconnectedGraph.dfsTraversal2(0) //0,1,3,2,4,5,6,7
}

/**
 * Theory In Short:
 * The idea of the depth-first search algorithm is to start from an arbitrary node of the graph
 * and to explore as far as possible before coming back i.e. moving to an adjacent node until there is no unvisited adjacent node.
 * Then backtrack and check for other unvisited nodes and repeat the same process for them.
 * Here backtracking means, "When all the neighbors of a vertex (say x) are visited, the algorithm ends for vertex x
 * so we return and check neighbors of the vertex that initiated the call to vertex x"
 */

/**
 * Complexity Analysis of DFS Algorithm
 * Time complexity: Since we consider every vertex exactly once and every edge atmost twice(once while backtracking), therefore time complexity is
 * O(V+2E)≃O(V+E), where
 * V is the number of vertices and
 * E is the number of edges in the given graph.

 * Space Complexity:
 * O(V), because we need an auxiliary visited array/set of size V
 */

/**
 *
 * Applications of Depth First Search:
 *
 * 1. Detecting cycle in a graph: A graph has a cycle if and only if we see a back edge during DFS.
 * So we can run DFS for the graph and check for back edges.
 *
 * 2. Path Finding: We can specialize the DFS algorithm to find a path between two given vertices u and z.
 *
 * Call DFS(G, u) with u as the start vertex.
 * Use a stack S to keep track of the path between the start vertex and the current vertex.
 * As soon as destination vertex z is encountered, return the path as the contents of the stack
 *
 * 3. Topological Sorting: Topological Sorting is mainly used for scheduling jobs from the given dependencies among jobs.
 * In computer science, applications of this type arise in instruction scheduling, ordering of formula cell evaluation
 * when recomputing formula values in spreadsheets, logic synthesis, determining the order of compilation tasks to perform in makefiles,
 * data serialization, and resolving symbol dependencies in linkers.
 *
 * 4. To test if a graph is bipartite: We can augment either BFS or DFS when we first discover a new vertex,
 * color it opposite its parents, and for each other edge, check it doesn’t link two vertices of the same color.
 * The first vertex in any connected component can be red or black.
 *
 * 5. Finding Strongly Connected Components of a graph: A directed graph is called strongly connected if
 * there is a path from each vertex in the graph to every other vertex.
 *
 * 6. Solving puzzles with only one solution: such as mazes.
 * (DFS can be adapted to find all solutions to a maze by only including nodes on the current path in the visited set.)
 *
 */