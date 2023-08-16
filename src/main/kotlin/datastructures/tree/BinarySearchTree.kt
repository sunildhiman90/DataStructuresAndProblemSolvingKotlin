package datastructures.tree


// Insertion, Deletion, and Search will be different, all other methods can be same as that of BinaryTree
class BinarySearchTree {

    class Node(var data: Int, var left: Node? = null, var right: Node? = null)

    var root: Node? = null
        private set

    private fun insert(node: Node?, dataToInsert: Int): Node {
        var node = node //becoz function variables are of type val, we need to create new var to update it
        if (node == null) {
            node = Node(dataToInsert) //create new node
            return node
        }

        if (dataToInsert < node.data) {
            node.left = insert(node.left, dataToInsert)
        } else {
            node.right = insert(node.right, dataToInsert)
        }

        return node //we are returning the same node here if its not null, for every num, in the last call it will return root, becoz we are starting from root
    }

    fun populate(nums: IntArray) {
        for (i in nums.indices) {
            root = insert(root, nums[i])
        }
    }

    // O(logn): This will search node with given nodeDataToFind, if found it wil return node else null
    fun searchNode(root: Node?, nodeDataToFind: Int): Node? {

        if (root == null) return null

        if (root.data == nodeDataToFind) return root

        return if (nodeDataToFind < root.data) {
            searchNode(root.left, nodeDataToFind)
        } else {
            searchNode(root.right, nodeDataToFind)
        }
    }

    fun search(root: Node?, nodeDataToFind: Int): Boolean {
        val node = searchNode(root, nodeDataToFind)
        return node != null
    }

    // Logic is similar to search, but when data is found instead of returning node,
    // we will perform delete logic and return accordingly, else we will apply deleteNode on left or right subtree
    fun deleteNode(root: Node?, nodeDataToDelete: Int): Node? {
        if (root == null) return null

        if (root.data == nodeDataToDelete) {

            //Delete Case 1: (No Child), both left and right are null
            if (root.left == null && root.right == null) {
                return null
            }

            //Delete Case 2: (One Child)
            if (root.left == null) {
                return root.right
            } else if (root.right == null) {
                return root.left
            }

            // Delete Case 3: (Two Children)
            val inorderSuccessor = findInorderSuccessor(root.right!!) // find inorder successor in right subtree
            root.data = inorderSuccessor.data //replace node data with inorder successor
            root.right = deleteNode(
                root.right,
                nodeDataToDelete = inorderSuccessor.data
            ) //delete inorderSuccessor and update right subtree
        }

        if (nodeDataToDelete < root.data) {
            root.left = deleteNode(root.left, nodeDataToDelete)
        } else {
            root.right = deleteNode(root.right, nodeDataToDelete)
        }

        return root
    }

    private fun findInorderSuccessor(node: Node): Node {
        // left most node
        var node: Node = node
        while (node.left != null) {
            node =
                node.left!! // we know it will not be null, so applying not null assertion, otherwise we should not use not null assertion !! operator
        }
        return node
    }

    fun display() {
        displayChildren(root)
    }

    private fun displayChildren(node: Node?) {
        if (node == null) {
            return
        }
        print("${node.data}\t")
        displayChildren(node.left)
        displayChildren(node.right)
    }

    fun prettyDisplay() {
        val level = 0
        prettyDisplayChildren(level, root)
    }

    // it will display 90 degree left rotated(from root) visual of tree
    private fun prettyDisplayChildren(level: Int, node: Node?) {
        if (node == null) {
            return
        }

        // traverse till rightmost node
        prettyDisplayChildren(level + 1, node.right)

        // print space according to level and finally node data
        if (level > 0) {
            for (i in 0 until level - 1) {
                print("\t")
            }
            println("\t---->${node.data}")
        } else {
            //print root and no space
            println(node.data)
        }

        //repeat same for left
        prettyDisplayChildren(level + 1, node.left)
    }
}

fun main() {
    // build BST From below given array
    //              4
    //         2        6
    //      1    3    5    7
    //                        9
    val arr = intArrayOf(4, 2, 1, 6, 3, 5, 7, 9) //first element will become root
    //val arr2 = intArrayOf(7, 4, 2, 1, 6, 3, 5) //first element will become root
    val binarySearchTree = BinarySearchTree()
    binarySearchTree.populate(arr)
    binarySearchTree.prettyDisplay() //it will display 90 degree left rotated visual of tree

    println()
    val search = 3
    //val search = 9
    val isFound = binarySearchTree.search(root = binarySearchTree.root, search)
    println("Is $search Found: = $isFound")

    println()
    val toDelete = 2
    binarySearchTree.deleteNode(root = binarySearchTree.root, nodeDataToDelete = toDelete)
    println("<---Tree after deletion of $toDelete--->")
    binarySearchTree.prettyDisplay()

}

/**
 * Search, Insert & Delete have same time complexity of O(height)
 * Best Case & Average Case Time Complexity: O(log(n))
 * Becoz at each level, we are searching only approximately in half nodes as compared to prev total,
 * by checking either we need to search in left or right subtree
 *
 * Worst Case Time Complexity: O(n)
 * When tree is skewed tree: unbalanced tree
 *
 */

/**
 * NOTES:
 *  All insertion, Deletion and search approaches are almost same in BST.
 *  In Insertion: If node == null, just insert and return node, else repeat same logic either in left or right subtree and update left and right subtree
 *  In Deletion: If node == null return null, and If node.data == nodeDataToDelete, just delete and finally return node,
 *  else repeat same logic either in left or right subtree and update left and right subtree
 *  In Search: If node == null return null, and If node.data == nodeDataToDelete, just return node,
 *  else repeat same logic either in left or right subtree
 *
 */
