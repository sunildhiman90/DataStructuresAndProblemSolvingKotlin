package problemsolving.consistenthashing.models


// It can be any entity like server in case of load balancing..
data class Node(var id: String, var ipAddress: String, var weight: Int = 0)