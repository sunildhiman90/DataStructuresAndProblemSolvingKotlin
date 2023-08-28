package problemsolving.recursion_backtracking.backtracking

// Given an array of positive integers arr[] ,
// The task is to find all unique combinations in arr[], same element can be repeated


// Approach 1, space complexity : O(n), Time compl: O(n*n!)
fun permutations2(list: List<Int>): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()
    val selected = BooleanArray(list.size)

    helperPermutations2(inputList = list.sorted(), output, selected = selected, currentList = mutableListOf())

    return output
}

fun helperPermutations2(
    inputList: List<Int>,
    output: MutableList<List<Int>>,
    selected: BooleanArray,
    currentList: MutableList<Int>,
) {

    if (currentList.size == inputList.size) {
        output.add(currentList.toList())
        return
    }

    for (i in inputList.indices) {

        // if prev element is same as current, and we have selected prev, then skip select current in sorted list
        if ((i > 0 && inputList[i] == inputList[i - 1]) && selected[i - 1]) continue

        if (!selected[i]) {
            selected[i] = true
            currentList.add(inputList[i])
            helperPermutations2(inputList, output, selected, currentList)
            selected[i] = false
            currentList.removeAt(currentList.size - 1)
        }
    }

}

// Approach 2: space complexity : O(1), Time compl: O(n*n!), this approach not working currently, need to adjust logic
//fun permutations2Approach2(list: List<Int>): MutableList<List<Int>> {
//    val output = mutableListOf<List<Int>>()
//
//    helperPermutations2Approach2(inputList = list.toMutableList(), output, ind = 0)
//
//    return output
//}
//
//fun helperPermutations2Approach2(
//    inputList: MutableList<Int>,
//    output: MutableList<List<Int>>,
//    ind: Int,
//) {
//
//    if (ind == inputList.size) {
//        output.add(inputList.toList())
//        return
//    }
//
//    for (i in ind until inputList.size) {
//
//        //due to swapping sorting order will not be maintained, so we need to check if we are swapping(i) with same next element(ind), then skip
//        if ((i > ind && inputList[i] == inputList[ind])) continue
//
//        swap2(inputList, ind, i) //for current recursion call loop
//        helperPermutations2Approach2(
//            inputList,
//            output,
//            ind + 1
//        ) // after this(ind+1) will finish, it will return back to prev recursion call(ind) loop in next line
//        swap2(inputList, ind, i) //for prev recursion call loop
//    }
//
//}

fun swap2(inputList: MutableList<Int>, ind: Int, i: Int) {
    val temp = inputList[ind]
    inputList[ind] = inputList[i]
    inputList[i] = temp
}

fun main() {
    //println(permutations2Approach2(mutableListOf(1, 1, 2, 3)))
    println(permutations2(mutableListOf(2, 2, 1, 1)))
    println(permutations2(mutableListOf(1, 1, 2)))
    //println(permutations2Approach2(mutableListOf(2, 2, 1, 1)))
}
