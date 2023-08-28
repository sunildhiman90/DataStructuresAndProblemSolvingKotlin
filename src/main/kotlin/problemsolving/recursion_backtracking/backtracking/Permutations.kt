package problemsolving.recursion_backtracking.backtracking

// Given an array of positive integers arr[] ,
// The task is to find all unique combinations in arr[], same element should not be repeated


// Approach 1, space complexity : O(n), Time compl: O(n*n!)
fun permutations(list: List<Int>): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()
    val selected = BooleanArray(list.size)

    helperPermutations(inputList = list, output, selected = selected, currentList = mutableListOf(), ind = 0)

    return output
}

fun helperPermutations(
    inputList: List<Int>,
    output: MutableList<List<Int>>,
    selected: BooleanArray,
    currentList: MutableList<Int>,
    ind: Int,
) {

    if (ind == inputList.size) {
        output.add(currentList.toList())
        return
    }

    for (i in inputList.indices) {
        if (!selected[i]) {
            selected[i] = true
            currentList.add(inputList[i])
            helperPermutations(inputList, output, selected, currentList, ind + 1)
            selected[i] = false
            currentList.remove(inputList[i])
        }
    }

}

// Approach 2: space complexity : O(1), Time compl: O(n*n!)
fun permutationsApproach2(list: List<Int>): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()

    helperPermutationsApproach2(inputList = list.toMutableList(), output, ind = 0)

    return output
}

fun helperPermutationsApproach2(
    inputList: MutableList<Int>,
    output: MutableList<List<Int>>,
    ind: Int,
) {

    if (ind == inputList.size) {
        output.add(inputList.toList())
        return
    }

    for (i in ind until inputList.size) {
        swap(inputList, ind, i) //for current recursion call loop
        helperPermutationsApproach2(
            inputList,
            output,
            ind + 1
        ) // after this(ind+1) will finish, it will return back to prev recursion call(ind) loop in next line
        swap(inputList, ind, i) //for prev recursion call loop
    }

}

fun swap(inputList: MutableList<Int>, ind: Int, i: Int) {
    val temp = inputList[ind]
    inputList[ind] = inputList[i]
    inputList[i] = temp
}

fun main() {
    println(permutations(mutableListOf(1, 2, 3)))
    println(permutationsApproach2(mutableListOf(1, 2, 3)))
    println(permutationsApproach2(mutableListOf(1, 1, 2)))
}
