package problemsolving.binarysearchtree

import datastructures.tree.BinarySearchTree

//Print all paths from root to all leafs

fun printRootToLeafPaths(root: BinarySearchTree.Node?, paths: MutableList<Int>) {
    if (root == null) return //for the nodes, which have one child as null

    paths.add(root.data)

    if (root.left == null && root.right == null) {
        //base case, it means we reached at leaf node
        printPath(paths)
    } else {
        //repeat same for non leaf nodes
        printRootToLeafPaths(root.left, paths)
        printRootToLeafPaths(root.right, paths)
    }

    paths.removeAt(paths.size - 1)

}

val allPathsFinalList = mutableListOf<List<Int>>()

fun printPath(paths: MutableList<Int>): String {
    allPathsFinalList.add(paths.toList())
    for (num in paths) {
        print("$num\t")
    }
    println()
    return paths.joinToString(separator = "->")
}

fun main() {

    // Build BST From below given array
    //              4
    //         2        6
    //      1    3    5    7
    //                        9
    val arr = intArrayOf(4, 2, 1, 6, 3, 5, 7, 9) //first element will become root
    val binarySearchTree = BinarySearchTree()
    binarySearchTree.populate(arr)
    binarySearchTree.prettyDisplay() //it will display 90 degree left rotated visual of tree

    printRootToLeafPaths(root = binarySearchTree.root, paths = mutableListOf())

    println(allPathsFinalList)
}