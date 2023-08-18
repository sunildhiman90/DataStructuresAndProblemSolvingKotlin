package datastructures.tree


// Insertion, Deletion, and Search will be different, all other methods can be same as that of BinaryTree
class AVLTree {

    class Node(var data: Int, var left: Node? = null, var right: Node? = null)

    var root: Node? = null
        private set

    private fun insert(node: Node?, dataToInsert: Int): Node? {
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

        return rotate(node) //we are returning the same node here if its not null, for every num, in the last call it will return root, becoz we are starting from root
    }

    private fun rotate(node: Node): Node? {

        // All This logic will be executed for one node only, that will become unbalanced after adding new node

        //Left Heavy
        if (heightOfNode(node.left) - heightOfNode(node.right) > 1) {
            //Case 1: Left-Left Heavy
            if (heightOfNode(node.left?.left) - heightOfNode(node.left?.right) > 0) {
                prettyDisplay()
                println("//////////////before_LL->${node.data}")
                return rightRotate(node) // right rotate parent
            }

            //Case 2: Left-Right Heavy
            if (heightOfNode(node.left?.left) - heightOfNode(node.left?.right) < 0) {
                prettyDisplay()
                println("//////////////before_LR->${node.data}")
                node.left = leftRotate(node.left) // left rotate child
                return rightRotate(node) // then right rotate parent
            }
        }

        //Right Heavy
        if (heightOfNode(node.left) - heightOfNode(node.right) < -1) {
            //Case 3: Right-Right Heavy
            if (heightOfNode(node.right?.left) - heightOfNode(node.right?.right) < 0) {
                prettyDisplay()
                println("//////////////before_RR->${node.data}")
                return leftRotate(node)
            }

            //Case 4: Right-Left Heavy
            if (heightOfNode(node.right?.left) - heightOfNode(node.right?.right) > 0) {
                prettyDisplay()
                println("//////////////before_RL->${node.data}")
                node.right = rightRotate(node.right) // right rotate child
                return leftRotate(node) // left rotate parent
            }
        }

        return node

    }

    private fun rightRotate(parent: Node?): Node? {

        val child = parent?.left // only these 2 nodes: parent?.left child & parent?.left.right will be changed
        val tree = child?.right

        child?.right = parent
        parent?.left = tree

        return child
    }

    private fun leftRotate(child: Node?): Node? {
        val parent = child?.right // only these 2 child.right & child.right.left nodes will be changed
        val tree = parent?.left

        child?.right = tree
        parent?.left = child

        return parent
    }


    fun populate(nums: IntArray) {
        for (i in nums.indices) {
            root = insert(root, nums[i])
        }
    }

    // O(logn): This will search node with given nodeDataToFind, if found it wil return node else null
    private fun searchNode(root: Node?, nodeDataToFind: Int): Node? {

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

    private fun heightOfTree(node: Node?): Int {
        if (node == null) {
            return 0
        }

        val leftSubtreeHeight = heightOfTree(node.left)
        val rightSubtreeHeight = heightOfTree(node.right)

        // we are adding one for current node, becoz each subtree has 3 nodes:  root, left & right and at each level change , height increases by 1
        return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1
    }

    private fun heightOfNode(nodeToFindHeight: Node?): Int {
        if (nodeToFindHeight == null) return 0

        //search node
        val node = searchNode(root, nodeToFindHeight.data)

        // return height of this subtree starting from node of which we are finding height
        return heightOfTree(node)
    }

    //TODO, delete

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
                print("\t\t")
            }
            println("\t--->${node.data}")
        } else {
            //print root and no space
            println(node.data)
        }

        //repeat same for left
        prettyDisplayChildren(level + 1, node.left)
    }
}

fun main() {
    // Following BST will be built From below given array
    //              4
    //         2         6
    //      1    3    5     7
    //                         9
    //                           11

    // And Following AVL will be built From below given array:
    // It includes 3 rotations, run the code and check in console before each case We have printed tree, We will understand better
    // After every insertion, it checks from that node going upwards for each node if unbalanced or not
    //              4
    //         2         6
    //      1    3    5     9
    //                    7   11
    val arr = intArrayOf(4, 2, 1, 6, 3, 5, 7, 9, 11) //first element will become root,
    //val arr2 = intArrayOf(7, 4, 2, 1, 6, 3, 5) //first element will become root
    val avlTree = AVLTree()
    avlTree.populate(arr)
    avlTree.prettyDisplay() //it will display 90 degree left rotated visual of tree

}

/**
 * Search, Insert & Delete have same time complexity of O(height) same as of BST
 * Insert:- It is same as BST(O(log(n))) + rotate(node) which only performs 2 steps ,means constant time,
 * So it will be O(logn) + 1 => O(logn)
 *
 * Best Case & Average Case Time Complexity: O(log(n))
 * Becoz at each level, we are searching only approximately in half nodes as compared to prev total,
 * by checking either we need to search in left or right subtree
 *
 * Worst Case Time Complexity: Actually no worst case will be there, becoze tree will be balancing itself.
 *
 */


