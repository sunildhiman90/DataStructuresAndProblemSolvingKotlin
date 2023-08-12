package datastructures.tree

import java.util.*

/**
 * Tips for Most of Tree Problems:
 * 1. Mostly we will use recursion, Solve the problem for left subtree by going to null of last left and then it will go to base condition.
 * 3. Repeat  Step 1 for right subtree as well, by going to null of last right and then it will go to base condition
 * 2. And finally Add base condition if node == null , then either perform some calculations there, or apply your terminating condition
 *
 */

class BinaryTreeWithProblemSolutions {

    class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    var root: Node? = null
        private set

    fun buildTree() {
        println("Enter the root Node: ")
        val data = readln().toInt()
        root = Node(data)
        this.buildTree(root)
    }

    private fun buildTree(node: Node?) {

        println("Do you want to enter the left of ${node?.data}: [true/false] ")
        val left = readln().toBoolean()
        if (left) {
            println("Enter the value of left of ${node?.data}: [true/false] ")
            val data = readln().toInt()
            node?.left = Node(data)
            this.buildTree(node?.left)
        }

        println("Do you want to enter the right of ${node?.data}: [true/false] ")
        val right = readln().toBoolean()
        if (right) {
            println("Enter the value of right of ${node?.data}: [true/false] ")
            val data = readln().toInt()
            node?.right = Node(data)
            this.buildTree(node?.right)
        }

    }

    var index = -1

    //This method will build tree from Preorder traversal NLR ->(NODE LEFT RIGHT)
    fun buildTreeFromPreorderArray(nodes: IntArray): Node? {
        index++
        if (nodes[index] == -1) {
            return null
        }

        val node = Node(nodes[index])

        //repeat same for left and right subtree
        node.left = buildTreeFromPreorderArray(nodes)
        node.right = buildTreeFromPreorderArray(nodes)
        root = node
        return root

    }

    fun preorderTraversal() {
        println("---PreOrder Traversal---")
        preorderTraversal(root)
    }

    // DFS -> NLR
    private fun preorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        print("${node.data}\t")
        preorderTraversal(node.left)
        preorderTraversal(node.right)
    }

    fun inorderTraversal() {
        println("---InOrder Traversal---")
        inorderTraversal(root)
    }

    // DFS -> LNR
    private fun inorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        inorderTraversal(node.left)
        print("${node.data}\t")
        inorderTraversal(node.right)
    }

    fun postorderTraversal() {
        println("---PostOrder Traversal---")
        postorderTraversal(root)
    }

    // DFS -> LNR
    private fun postorderTraversal(node: Node?) {
        if (node == null) {
            return
        }
        postorderTraversal(node.left)
        postorderTraversal(node.right)
        print("${node.data}\t")
    }


    // BFS, Using null for printing nextline for level
    fun levelOrderTraversal(start: Node? = root) {
        println("---Level Order Traversal---")
        if (root == null) return
        val queue: Queue<Node?> = LinkedList()

        // Mark visited and add to queue for printing and then checking its left and right child
        queue.add(start)
        queue.add(null) // for next level

        while (!queue.isEmpty()) {

            //poll and print
            val currentNode = queue.poll()
            //If case is just for printing new line for next level
            if (currentNode == null) {
                println()
                // just print new line and add null for next level if queue is not empty
                if (queue.isEmpty()) {
                    break
                } else queue.add(null) // for next level
            } else {
                //WHen we are picking up current element from queue, we are adding nodes of next level for that node
                print("${currentNode.data}\t")
                //check for its neighbours
                if (currentNode.left != null) {
                    queue.add(currentNode.left)
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)
                }
                // instead of adding null here, we will add it when removing null,
                // it means all the left and right subtrees will be added by that time of each node of same level,
                // otherwise it wil add null after each ( left and right ), SO null means start of next level
            }
        }
    }

    //It will return list at each level
    fun levelOrderTraversal2(start: Node? = root): List<List<Int>> {
        println("---Level Order Traversal2---")
        if (start == null) return emptyList()
        val queue: Queue<Node?> = LinkedList()
        val finalList = mutableListOf<List<Int>>()

        var level = 0
        // Mark visited and add to queue for printing and then checking its left and right child
        queue.add(start)
        queue.add(null) // for next level
        finalList.add(start?.data?.let { listOf(it) } ?: emptyList())
        level++

        while (!queue.isEmpty()) {

            //poll and print
            val currentNode = queue.poll()
            //If case is just for printing new line for next level
            if (currentNode == null) {
                println()
                // just print new line and add null for next level if queue is not empty
                if (queue.isEmpty()) {
                    break
                } else {
                    // for next level
                    queue.add(null)
                    level++
                }
            } else {
                print("${currentNode.data}\t")
                //check for its neighbours

                val list = if (finalList.size - 1 >= level) {
                    //if list already exist for this level, use that
                    finalList[level].toMutableList()
                } else {
                    // else new mutableList
                    mutableListOf()
                }
                if (currentNode.left != null) {
                    queue.add(currentNode.left)
                    currentNode.left?.data?.let { list.add(it) }
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)
                    currentNode.right?.data?.let { list.add(it) }
                }

                if (list.isNotEmpty()) {
                    if (finalList.size - 1 >= level) {
                        // update existing list at that level
                        finalList[level] = list
                    } else {
                        // add new list
                        finalList.add(list)
                    }
                }
            }
        }
        return finalList
    }

    // MAIN APPROACH: using the approach of [HeightDepthOfNodeUsingLevelOrderTraversal] without using null
    fun levelOrderTraversal3(start: Node? = root) {
        println("---Level Order Traversal 3---")
        var level = 0
        val queue: Queue<Node> = LinkedList()
        queue.add(start)

        while (!queue.isEmpty()) {
            print("Level: $level --> \t")

            val n = queue.size
            //Pick all elements at currently in the queue, at level : level, BEcoz everytime we are printing node, we are adding its left and right as, that is actually next level
            for (i in 0 until n) {
                val currentNode = queue.poll()
                print("${currentNode.data}  ")
                if (currentNode.left != null) {
                    queue.add(currentNode.left) //for next level
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)  //for next level
                }
            }
            println()
            level++
        }
    }

    // MAIN APPROACH: using the approach of [HeightDepthOfNodeUsingLevelOrderTraversal] without using null
    fun levelOrderTraversal3ReturningList(start: Node? = root): List<List<Int>> {
        if (start == null) return emptyList()
        var level = 0
        val finalList = mutableListOf<List<Int>>()
        val queue: Queue<Node> = LinkedList()
        queue.add(start)

        while (!queue.isEmpty()) {
            print("Level: $level --> \t")
            val listByLevel = mutableListOf<Int>()
            val n = queue.size
            // Pick all elements at currently in the queue, at level : $level, BEcoz everytime we are printing node, we are adding its left and right as, that is actually next level
            // this loop will run for all elements for level: $level
            for (i in 0 until n) {
                val currentNode = queue.poll()
                currentNode?.data?.let { listByLevel.add(it) }
                if (currentNode.left != null) {
                    queue.add(currentNode.left) //for next level
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right)  //for next level
                }
            }
            println()
            finalList.add(listByLevel)
            level++
        }
        return finalList
    }

    // This will search node with given nodeDataToFind, if found it wil return node else null
    fun searchNode(root: Node?, nodeDataToFind: Int): Node? {

        if (root == null) return null

        if (root.data == nodeDataToFind) return root

        return searchNode(root.left, nodeDataToFind) ?: searchNode(root.right, nodeDataToFind)
    }

    fun display() {
        println(root?.data)
        displayChildren(root?.left)
        displayChildren(root?.right)
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
        val level = 1
        prettyDisplayChildren(level, root)
    }

    // it will display 45 degree left rotated(from root) visual of tree
    private fun prettyDisplayChildren(level: Int, node: Node?) {
        if (node == null) {
            return
        }

        // traverse till rightmost node
        prettyDisplayChildren(level + 1, node.right)

        // print space according to level and finally node data
        if (level > 1) {
            for (i in 1 until level - 1) {
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

    // Binary Tree Problem 1: O(n)
    fun countNodes(node: Node? = root): Int {
        if (node == null) {
            return 0
        }

        val leftSubtreeCount = countNodes(node.left)
        val rightSubtreeCount = countNodes(node.right)

        // we are adding one for current node, becoz each subtree has 3 nodes:  root, left & right
        return leftSubtreeCount + rightSubtreeCount + 1
    }

    // Binary Tree Problem 2: O(n)
    fun sumOfNodes(node: Node? = root): Int {
        if (node == null) {
            return 0
        }

        val leftSubtreeSum = sumOfNodes(node.left)
        val rightSubtreeSum = sumOfNodes(node.right)

        // Add data of current node as well, becoz each subtree has 3 nodes:  root, left & right
        return leftSubtreeSum + rightSubtreeSum + node.data
    }

    // Binary Tree Problem 3: O(n)
    // This is based on if height of single root node is considered 1
    fun heightOfTree(node: Node? = root): Int {
        if (node == null) {
            return 0
        }

        val leftSubtreeHeight = heightOfTree(node.left)
        val rightSubtreeHeight = heightOfTree(node.right)

        // we are adding one for current node, becoz each subtree has 3 nodes:  root, left & right and at each level change , height increases by 1
        return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1
        //return leftSubtreeHeight.coerceAtLeast(rightSubtreeHeight) + 1 //alternative
    }

    // Binary Tree Problem 4: O(n*n)
    fun diameterOfTree(node: Node? = root): Int {
        if (node == null) {
            return 0
        }

        val leftSubtreeDiameter = diameterOfTree(node.left)
        val rightSubtreeDiameter = diameterOfTree(node.right)
        val rootDiameter = heightOfTree(node.left) + heightOfTree(node.right)

        return Math.max(rootDiameter, Math.max(leftSubtreeDiameter, rightSubtreeDiameter))
        //return rootDiameter.coerceAtLeast(leftSubtreeDiameter.coerceAtLeast(rightSubtreeDiameter))
    }

    // Binary Tree Problem 4 with Efficient Approach: O(n)
    // Calculate height along with diameter, But in prev approach we were not reusing the height for each subtree, every time we were calculating it again
    fun diameterOfTree2(node: Node? = root): NodeInfo {
        if (node == null) {
            return NodeInfo(0, 0)
        }

        val nodeLeftSubtreeInfo = diameterOfTree2(node.left)
        val nodeRightSubtreeInfo = diameterOfTree2(node.right)

        // calculations
        val treeHeight = Math.max(nodeLeftSubtreeInfo.height, nodeRightSubtreeInfo.height) + 1

        val nodeLeftSubtreeDiam = nodeLeftSubtreeInfo.diameter
        val nodeRightSubtreeDiam = nodeLeftSubtreeInfo.diameter
        val nodeDiam = nodeLeftSubtreeInfo.height + nodeRightSubtreeInfo.height

        val treeDiam = Math.max(nodeDiam, Math.max(nodeLeftSubtreeDiam, nodeRightSubtreeDiam))
        return NodeInfo(treeHeight, treeDiam)
    }

    class NodeInfo(val height: Int, val diameter: Int)
}

fun main() {
    // Uncomment this to test this way of building tree with prompting user
    /*val binaryTree = BinaryTreeWithProblemSolutions()
    binaryTree.buildTree()
    binaryTree.display()*/

    // build From Preorder Traversal, we will use -1 for null, as it is int array we cant directly add null in array
    //              1
    //         2        3
    //      4        5    6

    // Preorder traversal of above tree
    val arr = intArrayOf(1, 2, 4, -1, -1, -1, 3, 5, -1, -1, 6, -1, -1)
    val binaryTree2 = BinaryTreeWithProblemSolutions()
    binaryTree2.buildTreeFromPreorderArray(arr)
    binaryTree2.prettyDisplay() //it will display 45 degree left rotated visual of tree
    println()
    binaryTree2.preorderTraversal()
    println()
    binaryTree2.inorderTraversal()
    println()
    binaryTree2.postorderTraversal()
    println()
    binaryTree2.levelOrderTraversal()
    println()
    val count = binaryTree2.countNodes()
    println("Total no of nodes in tree : $count")

    println()
    val sum = binaryTree2.sumOfNodes()
    println("Total sum of data of all nodes in tree : $sum")

    println()
    val height = binaryTree2.heightOfTree()
    println("Total height of tree: $height")

    println()
    val diam = binaryTree2.diameterOfTree()
    println("Diameter of tree : $diam")

    println()
    val diam2 = binaryTree2.diameterOfTree2()
    println("Diameter of tree : ${diam2.diameter}")

    println()
    val finalListByLevel = binaryTree2.levelOrderTraversal2()
    println("List of data by levels of tree : $finalListByLevel")

    println()
    binaryTree2.levelOrderTraversal3()

    println()
    val listByLevels = binaryTree2.levelOrderTraversal3ReturningList()
    println(listByLevels)


    println()
    val nodeToFindHeight = 5
    val foundNode = binaryTree2.searchNode(binaryTree2.root, nodeToFindHeight)
    println("Node with data $nodeToFindHeight found : ${foundNode != null}")

    // inorder traversal
    val arr2 = intArrayOf(-1, 4, -1, 2, -1, 1, -1, 5, -1, 3, -1, 6, -1)

    // Random array
    val arr3 = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)


}

/**
 * DFS -> Depth First Traversals include PreOrder, InOrder and PostOrder Traversals
 * BFS -> Level Order Traversal
 *
 */