package problemsolving.recursion_backtracking.backtracking

// Given a String ,
// The task is to find all unique combinations of chars in string, same element should not be repeated


// Approach 1, space complexity : O(n), Time compl: O(n*n!)
fun permutationsOfString(list: List<Char>): MutableList<String> {
    val output = mutableListOf<String>()
    val selected = BooleanArray(list.size)

    helperPermutationsOfString(inputList = list, output, selected = selected, currentList = mutableListOf(), ind = 0)

    return output
}

fun helperPermutationsOfString(
    inputList: List<Char>,
    output: MutableList<String>,
    selected: BooleanArray,
    currentList: MutableList<Char>,
    ind: Int,
) {

    if (ind == inputList.size) {
        output.add(currentList.joinToString(""))
        return
    }

    for (i in inputList.indices) {
        if (!selected[i]) {
            selected[i] = true
            currentList.add(inputList[i])
            helperPermutationsOfString(inputList, output, selected, currentList, ind + 1)
            selected[i] = false
            currentList.remove(inputList[i])
        }
    }

}

// Approach 2: space complexity : O(1), Time compl: O(n*n!)
fun permutationsOfString2(list: List<Char>): MutableList<String> {
    val output = mutableListOf<String>()

    helperPermutationsOfString2(inputList = list.toMutableList(), output, ind = 0)

    return output
}

fun helperPermutationsOfString2(
    inputList: MutableList<Char>,
    output: MutableList<String>,
    ind: Int,
) {

    if (ind == inputList.size) {
        output.add(inputList.joinToString(""))
        return
    }

    for (i in ind until inputList.size) {
        swap(inputList, ind, i) //for current recursion call loop
        helperPermutationsOfString2(
            inputList,
            output,
            ind + 1
        ) // after this(ind+1) will finish, it will return back to prev recursion call(ind) loop in next line
        swap(inputList, ind, i) //for prev recursion call loop
    }

}

fun swap(inputList: MutableList<Char>, ind: Int, i: Int) {
    val temp = inputList[ind]
    inputList[ind] = inputList[i]
    inputList[i] = temp
}

fun main() {
    println(permutationsOfString("ABC".toMutableList()))
    println(permutationsOfString2("ABC".toMutableList()))
}
