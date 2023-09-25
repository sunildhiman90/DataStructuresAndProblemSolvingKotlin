package problemsolving.consistenthashing

import problemsolving.consistenthashing.models.Node
import problemsolving.consistenthashing.models.Request
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentSkipListMap
import java.util.concurrent.CopyOnWriteArrayList


class ConsistentHashing(
    hashFunction: (String) -> Long,
    numOfReplicas: Int
) : Router {

    //it will only maintain all positions or replicas by nodes
    private val nodePositions: MutableMap<Node, MutableList<Long>>

    //it will act as circular ring of nodes, using ConcurrentSkipListMap here becoz it is better for large records and non null values and for multithreaded environment
    private val nodeMappings: ConcurrentSkipListMap<Long, Node>
    private val hashFunction: (String) -> Long // Java alternative :- Function<String, Long> hashFunction; where String is input type, and Long is return type
    private val numOfReplicas: Int

    init {
        require(numOfReplicas != 0)
        this.numOfReplicas = numOfReplicas
        this.hashFunction = hashFunction
        nodePositions = ConcurrentHashMap<Node, MutableList<Long>>()
        nodeMappings = ConcurrentSkipListMap<Long, Node>()
    }

    override fun addNode(node: Node) {
        nodePositions[node] = CopyOnWriteArrayList()
        for (i in 0 until numOfReplicas) {
            val point: Long = hashFunction.invoke((i * numOfReplicas).toString() + node.id)
            nodePositions[node]?.add(point)
            nodeMappings[point] = node
        }
    }

    override fun removeNode(node: Node) {
        //remove from nodePositions and nodeMappings
        val positionsOfNode = nodePositions.remove(node) ?: emptyList()
        for (point in positionsOfNode) {
            nodeMappings.remove(point)
        }
    }

    override fun getAssignedNode(request: Request): Node {
        val key: Long = hashFunction.invoke(request.id) //use same hash function which was used to server id hashing

        //First check node at same hash, else check for next immediate node
        val entry: Map.Entry<Long, Node>? = nodeMappings.entries.find { it.key == key } ?: nodeMappings.higherEntry(key)

        //And if not entry found from prev line, then just assign first node
        return entry?.value ?: nodeMappings.firstEntry().value
    }
}