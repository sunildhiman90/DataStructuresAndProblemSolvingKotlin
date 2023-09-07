package problemsolving.consistenthashing

import problemsolving.consistenthashing.models.Node
import problemsolving.consistenthashing.models.Request

interface Router {
    fun addNode(node: Node)
    fun removeNode(node: Node)
    fun getAssignedNode(request: Request): Node
}