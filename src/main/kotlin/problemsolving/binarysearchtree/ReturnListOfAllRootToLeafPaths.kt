package problemsolving.binarysearchtree

import datastructures.tree.BinarySearchTree

//Return all paths from root to all leafs, backtracking problem

fun helper(root: BinarySearchTree.Node?, paths: MutableList<Int>, allPaths: MutableList<List<Int>>) {
    //Bounding condition, Backtrack from here
    if (root == null) return //for the nodes, which have one child as null, backtrack

    //Inclusion
    paths.add(root.data)

    if (root.left == null && root.right == null) {
        //Base case, it means we reached at leaf node, add path and return
        allPaths.add(paths)
        return // return since we don't want to check after leaf node
    }

    //repeat same for other non leaf nodes for other solutions, pass new list of paths here by using toMutableList,
    // otherwise it will keep old elements as well, Explanation is given at the bottom for this:
    helper(root.left, paths.toMutableList(), allPaths)
    helper(
        root.right,
        paths.toMutableList(),
        allPaths
    ) // when left part will return , then it will not pass that updated paths list, but instead the prev one from where it was called.

}

fun returnListOfPaths(
    root: BinarySearchTree.Node?,
): MutableList<List<Int>> {

    val allPaths = mutableListOf<List<Int>>()

    if (root == null) return allPaths

    val paths = mutableListOf<Int>()

    // it will build allPaths using recursion and backtracking
    helper(root, paths, allPaths)

    return allPaths
}


fun buildPathString(paths: MutableList<Int>) {
    for (num in paths) {
        print("$num\t")
    }
    println()
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

    val allPaths = returnListOfPaths(root = binarySearchTree.root)
    println(allPaths)
}


/**
 *
 * WHy we are passing new list for both left and right subtree?
 * Becoz when left part will return from leaf node , then if we pass same old list for paths
 * then it will become [4,2,1] and then for 2's right subtree call,  it will pass that list and add the new node to that list,
 * then it wil become [4,2,1,3], it will add right of 2 that is 3, to the same list, But we dont want that, Otherwise it will return wrong paths
 * as given below :
 * [[4, 2, 1, 3, 6, 5, 7, 9], [4, 2, 1, 3, 6, 5, 7, 9], [4, 2, 1, 3, 6, 5, 7, 9], [4, 2, 1, 3, 6, 5, 7, 9]]
 *  But it we pass new reference of list then it will pass, [4,2] for 2 as root for both left subtree and right subtree call.
 * SO thats why at each subtree call, we are passing new reference of list
 *
 */