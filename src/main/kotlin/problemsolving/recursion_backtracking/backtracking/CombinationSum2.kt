package problemsolving.recursion_backtracking.backtracking

// Given an array of positive integers arr[] and an integer target,
// The task is to find all unique combinations in arr[] where the sum is equal to target, same element can not be repeated.

// THis approach has issues with repeated elements, it produces repeated combinations then,
// use subtracting sum and loop approach: combinationSum2Alt1_2

//fun combinationSum2(list: List<Int>, target: Int): MutableList<List<Int>> {
//    val output = mutableListOf<List<Int>>()
//
//    helper2(inputList = list.sorted(), target, output, currentList = mutableListOf(), currentSum = 0, i = 0)
//
//    return output
//}
//
//fun helper2(
//    inputList: List<Int>,
//    target: Int,
//    output: MutableList<List<Int>>,
//    currentList: MutableList<Int>,
//    currentSum: Int,
//    i: Int,
//) {
//    var currentSum = currentSum
//
//    //Bounding condition
//    if (currentSum >= target) {
//        if (currentSum == target) {
//            val finalList = currentList.toList()  //need to convert MutableList currentList to List
//            output.add(finalList)
//        }
//        //we are done with result for current iteration, return(for excluding and checking with next iteration)
//        return //backtrack
//    }
//
//    // After last iteration, return, we dont have more elements, we need to backtrack and check with prev sublist with all iterations
//    if (i == inputList.size) {
//        return
//    }
//
//    //Inclusion:
//    currentSum += inputList[i]
//    currentList.add(inputList[i])
//
//    //Next solution
//    helper2(
//        inputList,
//        target,
//        output,
//        currentList,
//        currentSum,
//        i + 1
//    ) //check current element with next element here becoz same element can not be repeated, so we dont need to repeat for same
//
//    //Exclusion: We are Backtracked here if currentSum >= b and now exclude current solution and try for next iteration i
//    currentSum -= inputList[i]
//    currentList.remove(inputList[i])
//
//    // if same element is repeated in input, dont check for it again, instead check for next, it will produce repeated combinations
//    val ind = if (i > 0 && inputList[i] == inputList[i - 1]) i + 2 else i + 1
//
//    helper2(
//        inputList,
//        target,
//        output,
//        currentList,
//        currentSum,
//        ind
//    ) //after backtracking, check prev list[list-current element] combination with next element
//
//}


fun combinationSum2Alt1(list: List<Int>, target: Int): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()

    helperAlt1Com2(inputList = list, target = target, output = output, currentList = mutableListOf(), i = 0)

    return output
}

fun helperAlt1Com2(
    inputList: List<Int>,
    target: Int,
    output: MutableList<List<Int>>,
    currentList: MutableList<Int>,
    i: Int,
) {

    if (target == 0) {
        output.add(currentList.toList())
        return
    } else if (target < 0 || i == inputList.size) {
        return
    } else {
        //Inclusion:
        currentList.add(inputList[i])
        helperAlt1Com2(
            inputList,
            target - inputList[i],
            output,
            currentList,
            i + 1
        ) //check current element with next element here becoz same element can not be repeated, so we dont need to repeat for same

        //Exclusion: We are Backtracked here if target == b and now exclude current solution and try for next iteration i
        currentList.remove(inputList[i])

        //Next Solution: after backtracking, check prev list[list-current element] combination with next element
        helperAlt1Com2(inputList, target, output, currentList, i + 1)
    }

}


fun combinationSum2Alt1_2(list: List<Int>, target: Int): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()

    // pass the sorted list here, becoz same element is repeated in list, so that we can skip next same element otherwise it will
    // produce duplicate combinations
    helperAlt1Com2_1(
        inputList = list.sorted(),
        target = target,
        output = output,
        currentList = mutableListOf(),
        ind = 0
    )

    return output
}

// helperAlt1Com2 can be done this way also
fun helperAlt1Com2_1(
    inputList: List<Int>,
    target: Int,
    output: MutableList<List<Int>>,
    currentList: MutableList<Int>,
    ind: Int,
) {

    //due to last element == target, then it will not come inside this condition becoz for loop will not run beyond last element,
    // so that last element will not be printed then, thats why we need to use it outside loop
    if (target == 0) {
        output.add(currentList.toList())
        return
        //backtrack and check for next
    }

    for (i in ind until inputList.size) {

        if (target < 0) {
            break
        }

        if (i > ind && inputList[i] == inputList[i - 1]) {
            continue //if element is repeated, then skip this and check for next
        }

        //Inclusion:
        currentList.add(inputList[i])
        helperAlt1Com2_1(
            inputList,
            target - inputList[i],
            output,
            currentList,
            i + 1
        ) //check for this element i again, becoz same element can be repeated

        //Exclusion: We are Backtracked here if target == b and now exclude current solution and try for next iteration i
        currentList.remove(inputList[i])

    }

}


fun main() {
    //println(combinationSum2(mutableListOf(2, 4, 6, 8), 8))
    println(combinationSum2Alt1_2(mutableListOf(10, 1, 2, 7, 6, 1, 5), 8))
    //println(combinationSum2Alt1(mutableListOf(2, 4, 6, 8), 8))
    //println(combinationSum2Alt1_2(mutableListOf(2, 4, 6, 8), 8))
    //println(combinationSumAlt2_2(mutableListOf(2, 4, 6, 8), 8))
}


/**
 *
 * Time Complexity:O(2^n*k)
 *
 * Reason: Assume if all the elements in the array are unique then the no. of subsequence you will get will be O(2^n).
 * we also add the currentList to our output when we reach the base case that will take “k”//average space for the currentList.
 *
 * Space Complexity:O(k*x)
 *
 * Reason: if we have x combinations then space will be x*k where k is the average length of the combination.
 */