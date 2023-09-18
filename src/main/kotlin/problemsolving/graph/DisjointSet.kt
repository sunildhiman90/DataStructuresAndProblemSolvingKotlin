package problemsolving.graph


// Time Complexity :- O(4 * alpha) ~= O(1),   Almost equal to constant time
class DisjointSet(n: Int) {

    private val rank = mutableListOf<Int>()
    private val parent = mutableListOf<Int>()
    private val size = mutableListOf<Int>()

    init {
        for (i in 0..n) {
            rank.add(0)
            parent.add(i)
            size.add(1)
        }
    }

    fun findPar(node: Int): Int {
        if (parent[node] == node) return node

        val ulp = findPar(parent[node])
        parent[node] = ulp
        return parent[node]
    }

    fun unionByRank(u: Int, v: Int) {
        val ulpU = findPar(u)
        val ulpV = findPar(v)

        //already in the same component
        if (ulpU == ulpV) return

        //if not in the same component, compare ranks
        if (rank[ulpU] < rank[ulpV]) {
            parent[ulpU] = ulpV
        } else if (rank[ulpV] < rank[ulpU]) {
            parent[ulpV] = ulpU
        } else {
            //same rank
            parent[ulpV] = ulpU
            rank[ulpU]++
        }

    }

    fun unionBySize(u: Int, v: Int) {
        val ulpU = findPar(u)
        val ulpV = findPar(v)

        //already in the same component
        if (ulpU == ulpV) return

        //if not in the same component, compare sizes
        if (size[ulpU] < size[ulpV]) {
            parent[ulpU] = ulpV
            size[ulpV] += size[ulpU]
        } else {
            //same size
            parent[ulpV] = ulpU
            size[ulpU] += size[ulpV]
        }

    }

}

fun main() {
    val ds = DisjointSet(7)
    ds.unionByRank(1, 2)
    ds.unionByRank(2, 3)
    ds.unionByRank(4, 5)
    ds.unionByRank(5, 6)
    ds.unionByRank(6, 7)

    //if 3 or 7 belong to same component
    if (ds.findPar(3) == ds.findPar(7))
        println("Belong to Same component")
    else
        println("Not belong to same component")

    ds.unionByRank(3, 7)

    //now again check if 3 or 7 belong to same component
    if (ds.findPar(3) == ds.findPar(7))
        println("Belong to Same component")
    else
        println("Not belong to same component")


}