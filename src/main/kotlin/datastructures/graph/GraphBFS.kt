package datastructures.graph

import java.util.*


class Edge(val src: Int, val dest: Int, val weight: Int = 0)

class Graph(
    var numberOfVertices: Int,
    var adjacencyList: Array<LinkedList<Edge>> = Array(numberOfVertices) {
        LinkedList()
    }
) {

    fun addEdge(from: Int, to: Int, weight: Int = 0) {
        adjacencyList[from].add(Edge(from, to, weight))
    }

    // MAIN APPROACH
    fun bfsTraversalMain(startVertex: Int) {
        val visited: Array<Boolean> =
            Array(numberOfVertices) { false } //instead of Boolean array, we can use Set as well
        bfsHelperMain(startVertex, visited)

        // for disconnected graph
        // check if still some vertex is still unvisited
        for (i in visited.indices) {
            if (!visited[i]) {
                bfsHelperMain(i, visited)
            }
        }
    }

    private fun bfsHelperMain(startVertex: Int, visited: Array<Boolean>) {
        val queue: LinkedList<Int> = LinkedList()

        // mark visited and add to queue for printing and then checking its neighbors
        visited[startVertex] = true
        queue.add(startVertex)

        while (!queue.isEmpty()) {

            val n = queue.size
            //pick all elements at current level and add their neighbours for next level
            for (i in 0 until n) {
                //poll and print
                val currentVertex = queue.poll()
                print("$currentVertex \t")

                //check for its neighbours
                for (element in adjacencyList[currentVertex]) {
                    if (!visited[element.dest]) {
                        visited[element.dest] = true
                        queue.add(element.dest)
                    }
                }
            }
            println()
        }

    }


    // MAIN APPROACH with level
    fun bfsTraversalMainWithLevel(startVertex: Int) {
        val visited: Array<Boolean> =
            Array(numberOfVertices) { false } //instead of Boolean array, we can use Set as well

        bfsHelperMainWithLevel(startVertex, visited)

        // for disconnected graph
        // check if still some vertex is still unvisited
        for (i in visited.indices) {
            if (!visited[i]) {
                bfsHelperMainWithLevel(i, visited)
            }
        }
    }

    private fun bfsHelperMainWithLevel(startVertex: Int, visited: Array<Boolean>) {
        val queue: LinkedList<Int> = LinkedList()

        // mark visited and add to queue for printing and then checking its neighbors
        visited[startVertex] = true
        queue.add(startVertex)
        var level = 0

        while (!queue.isEmpty()) {

            print("Level: $level --> \t")

            val n = queue.size
            //pick all elements at current level and add their neighbours for next level
            for (i in 0 until n) {
                //poll and print
                val currentVertex = queue.poll()
                print("$currentVertex \t")

                //check for its neighbours
                for (element in adjacencyList[currentVertex]) {
                    if (!visited[element.dest]) {
                        visited[element.dest] = true
                        queue.add(element.dest)
                    }
                }
            }
            println()
            level++

        }

    }


    // Alternative approach
    fun bfsTraversalAlt(startVertex: Int) {
        val visited: Array<Boolean> =
            Array(numberOfVertices) { false } //instead of Boolean array, we can use Set as well
        bfsHelperAlt(startVertex, visited)

        // for disconnected graph
        // check if still some vertex is still unvisited
        for (i in visited.indices) {
            if (!visited[i]) {
                bfsHelperAlt(i, visited)
            }
        }
    }

    private fun bfsHelperAlt(startVertex: Int, visited: Array<Boolean>) {
        val queue: LinkedList<Int> = LinkedList()

        // mark visited and add to queue for printing and then checking its neighbors
        visited[startVertex] = true
        queue.add(startVertex)

        while (!queue.isEmpty()) {

            //poll and print
            val currentVertex = queue.poll()
            println("$currentVertex ")

            //check for its neighbours
            for (element in adjacencyList[currentVertex]) {
                if (!visited[element.dest]) {
                    visited[element.dest] = true
                    queue.add(element.dest)
                }
            }
        }

    }


    // Alternative approach with level
    fun bfsTraversalAltWithLevel(startVertex: Int) {
        val visited: Array<Boolean> =
            Array(numberOfVertices) { false } //instead of Boolean array, we can use Set as well
        val levels: Array<Int> = Array(numberOfVertices) { 0 }

        bfsHelperAltWithLevel(startVertex, visited, levels)

        // for disconnected graph
        // check if still some vertex is still unvisited
        for (i in visited.indices) {
            if (!visited[i]) {
                bfsHelperAltWithLevel(i, visited, levels)
            }
        }
    }

    // logic for levels will work only if we are traversing from start node
    private fun bfsHelperAltWithLevel(startVertex: Int, visited: Array<Boolean>, levels: Array<Int>) {
        val queue: LinkedList<Int> = LinkedList()

        // mark visited and add to queue for printing and then checking its neighbors
        // TODO, this logic for levels will work only if we are traversing from start node, need to implement for the case when we start from some other nodes
        visited[startVertex] = true
        levels[startVertex] = 0
        queue.add(startVertex)

        while (!queue.isEmpty()) {

            //poll and print
            val currentVertex = queue.poll()
            println("$currentVertex --- level from parent: ${levels[currentVertex]}")

            //check for its neighbours
            for (element in adjacencyList[currentVertex]) {
                if (!visited[element.dest]) {
                    visited[element.dest] = true
                    levels[element.dest] = levels[currentVertex] + 1 //get level of parent and increase it
                    queue.add(element.dest)
                }
            }
        }

    }
}

fun createConnectedGraphForTesting(): Graph {
    val graph = Graph(6)
    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(0, 5)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)
    return graph
}

fun createDisonnectedGraphForTesting(): Graph {
    val disconnectedGraph = Graph(8)
    disconnectedGraph.addEdge(0, 1)
    disconnectedGraph.addEdge(0, 2)
    disconnectedGraph.addEdge(0, 5)
    disconnectedGraph.addEdge(1, 3)
    disconnectedGraph.addEdge(2, 4)
    disconnectedGraph.addEdge(6, 7)
    return disconnectedGraph
}

fun main() {

    println("---Connected graph--- ")

    val graph = createConnectedGraphForTesting()

    graph.bfsTraversalMain(0) //0,1,2,5,3,4
    graph.bfsTraversalAltWithLevel(0) //0,1,2,5,3,4


    println("---Disconnected graph--- ")
    val disconnectedGraph = createDisonnectedGraphForTesting()

    disconnectedGraph.bfsTraversalMain(0) //0,1,2,5,3,4,6,7
    disconnectedGraph.bfsTraversalMainWithLevel(0) //0,1,2,5,3,4,6,7
}

/**
 * Theory
 * Breadth-First Search focuses on traversing Breadth wise, i.e.
 * we will first traverse all the neighbours of A first, and then explore their neighbours, and so on.
 *
 * Level: The level can be thought of as the distance from the starting Node, like 1, 2 and 5 are at distance 1 from 0
 * so they have level 1, and similarly, 3 is at distance 2(as it connects to 0 via 1 in between) wrt 0.
 *
 * So as we can see in the above image, we traverse the elements of each level first, before traversing the elements of other levels.
 * Due to the same reason, Breadth First Search can be also thought of as a Level Order Traversal
 */


/**
 * Complexity Analysis of BFS
 *
Time Complexity Analysis:
If we notice carefully, Breadth-First Search tries to visit one vertex at a time, until all are visited. From each vertex, we explore all of its neighbouring vertices, which is nothing but iterating all vertices connected to it on all of its edges.

So this essentially boils down to visiting each Vertex, and we have V total vertices.

From each V, we iterate all of the other neighbour vertices i.e. at the other end of all of its edges, and the total edges that we can have in the graph is E. Then it means Breadth-First Search works in O(E + V) time.

Space Complexity Analysis:
We use two data structures in Breadth-First Search - the visited array/Set, and the Queue.
A Visited array will have the size of the number of vertices in the graph (Similarly, the maximum size of a Set can also be up to the number of vertices in the graph).

Queue carries the vertices, as it explores them, and the maximum nodes it can have is again as many as the vertices in the Graph. E.g. If the starting vertex is 0 and all other vertices are connected to 0, then when we explore 0, we add all other vertices(as those are neighbours of 0) to Queue. So, the Queue will carry V-1 vertices in the worst case.
Since both the Visited array and Queue can have a max size of V(equal to as many vertices), the overall space complexity will be O(V).

It can also be noted here that the levels dictate the shortest distance(i.e. Number of edges) between two Nodes. E.g. if we start from A, then D is seen at level 1, so A and D have a shortest distance of 1 between them. Similarly, as B is seen at level 2, A and B have a shortest distance of 2.

So in case if you are ever interested in finding the shortest distance between any two nodes of a graph, Breadth-First Search is your best bet.

You just need to perform a Breadth First Search from one of the two nodes and find the level of the other node. This level will be your shortest distance between those two nodes.
 */

/**
 * Applications of Breadth First Search Algorithm
 *
 * Let’s try to see where does Breadth-First Search finds its applications:
 *
 * 1. Minimum spanning tree for unweighted graphs- In Breadth-First Search we can reach from any given source vertex to another vertex,
 * with the minimum number of edges, and this principle can be used to find the minimum spanning tree which is the path covering all vertices in the shortest paths.
 * 2. Peer to peer networking: In Peer to peer networking, to find the neighbouring peer from any other peer, the Breadth-First Search is used.
 * 3. Crawlers in search engines: Search engines need to crawl the internet. To do so, they can start from any source page,
 * follow the links contained in that page in the Breadth-First Search manner, and therefore explore other pages.
 * 4. GPS navigation systems: To find locations within a given radius from any source person, we can find all neighbouring locations
 * using the Breadth-First Search, and keep on exploring until those are within the K radius.
 * 5. Broadcasting in networks: While broadcasting from any source, we find all its neighbouring peers and continue broadcasting to them, and so on.
 * 6. Path Finding: To find if there is a path between 2 vertices, we can take any vertex as a source, and keep on traversing until we reach the destination vertex.
 * If we explore all vertices reachable from the source and could not find the destination vertex, then that means there is no path between these 2 vertices.
 * 7. Finding all reachable Nodes from a given Vertex: All vertices which are reachable from a given vertex can be found using the BFS approach in any disconnected graph.
 * The vertices which are marked as visited in the visited array after the BFS is complete contains all those reachable vertices.
 */
