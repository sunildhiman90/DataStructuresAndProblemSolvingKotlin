package problemsolving.recursion_backtracking.backtracking

// Given an array of positive integers arr[] and an integer target,
// The task is to find all unique combinations in arr[] where the sum is equal to target. same element can be repeated.


fun combinationSum(list: List<Int>, target: Int): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()

    helper(inputList = list, target, output, currentList = mutableListOf(), currentSum = 0, i = 0)

    return output
}

fun helper(
    inputList: List<Int>,
    target: Int,
    output: MutableList<List<Int>>,
    currentList: MutableList<Int>,
    currentSum: Int,
    i: Int,
) {
    var currentSum = currentSum

    //Bounding condition
    if (currentSum >= target) {
        if (currentSum == target) {
            output.add(currentList.toList()) //need to convert MutableList currentList to List
            //we are done with result for current iteration, return(for excluding and checking with next iteration)
        }
        return //backtrack
    }

    // After last iteration, return, we dont have more elements, we need to backtrack and check with prev sublist with all iterations
    if (i == inputList.size) {
        return
    }

    //Inclusion:
    currentSum += inputList[i]
    currentList.add(inputList[i])
    helper(
        inputList,
        target,
        output,
        currentList,
        currentSum,
        i
    ) //check current element with this element i again, becoz same element can be repeated

    //Exclusion: We are Backtracked here if currentSum >= b and now exclude current solution and try for next iteration i
    currentSum -= currentList[currentList.size - 1]
    currentList.removeAt(currentList.size - 1)

    //Next Solution:
    helper(
        inputList,
        target,
        output,
        currentList,
        currentSum,
        i + 1
    ) //after backtracking, check prev list(currentList-current element) combination with next element

}

fun combinationSumAlt1(list: List<Int>, target: Int): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()

    helperAlt1(inputList = list, target = target, output = output, currentList = mutableListOf(), i = 0)

    return output
}

// Without using extra parameter for currentSum, instead we will manipulate target itself
fun helperAlt1(
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
        helperAlt1(
            inputList,
            target - inputList[i],
            output,
            currentList,
            i
        ) //check current element with this element i again, becoz same element can be repeated

        //Exclusion: We are Backtracked here if target == b and now exclude current solution and try for next iteration i
        currentList.removeAt(currentList.size - 1)

        //Next Solution: after backtracking, check prev list(currentList-current element) combination with next element
        helperAlt1(inputList, target, output, currentList, i + 1)
    }

}


//MAIN APPROACH

fun combinationSumAlt2(list: List<Int>, target: Int): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()

    helperAlt2(inputList = list, target = target, output = output, currentList = mutableListOf(), ind = 0)

    return output
}

fun helperAlt2(
    inputList: List<Int>,
    target: Int,
    output: MutableList<List<Int>>,
    currentList: MutableList<Int>,
    ind: Int,
) {

    if (target == 0) {
        output.add(currentList.toList())
        return // backtrack and check for next
    }

    for (i in ind until inputList.size) {

        if (target < 0) {
            break
        } else {
            //Inclusion:
            currentList.add(inputList[i])
            helperAlt2(
                inputList,
                target - inputList[i],
                output,
                currentList,
                i
            ) //check current element with this element i again, becoz same element can be repeated

            //Exclusion: We are Backtracked here if target == b and now exclude current solution and try for next iteration i
            currentList.removeAt(currentList.size - 1)
        }

    }

}


fun combinationSumAlt1_2(list: List<Int>, target: Int): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()

    helperAlt1_2(inputList = list, target = target, output = output, currentList = mutableListOf(), i = 0)

    return output
}

// Same combinationSumAlt1 can be done as this way also
fun helperAlt1_2(
    inputList: List<Int>,
    target: Int,
    output: MutableList<List<Int>>,
    currentList: MutableList<Int>,
    i: Int,
) {

    if (target <= 0 || i == inputList.size) {
        if (target == 0) {
            output.add(currentList.toList())
        }
        return
    }

    //Inclusion:
    currentList.add(inputList[i])
    helperAlt1_2(
        inputList,
        target - inputList[i],
        output,
        currentList,
        i
    ) //check current element with this element i again, becoz same element can be repeated

    //Exclusion: We are Backtracked here if target == b and now exclude current solution and try for next iteration i
    currentList.removeAt(currentList.size - 1)

    //Next Solution: after backtracking, check prev list(currentList-current element) combination with next element
    helperAlt1_2(inputList, target, output, currentList, i + 1)

}


fun combinationSumAlt2_2(list: List<Int>, target: Int): MutableList<List<Int>> {
    val output = mutableListOf<List<Int>>()

    helperAlt2_2(inputList = list, target = target, output = output, currentList = mutableListOf(), ind = 0)

    return output
}

// combinationSumAlt2 can be done as this way also
fun helperAlt2_2(
    inputList: List<Int>,
    target: Int,
    output: MutableList<List<Int>>,
    currentList: MutableList<Int>,
    ind: Int,
) {

    if (target == 0) {
        output.add(currentList.toList())
        return //backtrack and check for next
    }

    for (i in ind until inputList.size) {

        if (target < 0) {
            break
        } else {
            //Inclusion:
            currentList.add(inputList[i])
            helperAlt2_2(
                inputList,
                target - inputList[i],
                output,
                currentList,
                i
            ) //check current element with this element i again, becoz same element can be repeated

            //Exclusion: We are Backtracked here if target == b and now exclude current solution and try for next iteration i
            currentList.removeAt(currentList.size - 1)
        }

    }

}

fun main() {
    println(combinationSum(mutableListOf(2, 4, 6, 8), 8))
    println(combinationSumAlt1(mutableListOf(2, 4, 6, 8), 8))
    println(combinationSumAlt1_2(mutableListOf(2, 4, 6, 8), 8))
    println(combinationSumAlt2(mutableListOf(2, 4, 6, 8), 8))
    println(combinationSumAlt2_2(mutableListOf(2, 4, 6, 8), 8))
    //println(combinationSum(mutableListOf(2), 1))
    //println(combinationSum(mutableListOf(7, 2, 6, 5), 16))
}


/**
 *  EXPLANATION for combinationSum:-
 *
 *  Lets say for 2,4,6,8,
 *
 *  STEP 1
 *  1. First it will keep adding 2, and when 2,2,2,2, then it will backtrack
 *  AND come to exclusion step, it will remove last 2, and it will become [2,2,2]
 *  2. and with [2,2,2] sublist it will check with all next iterations with 4, 6, 8
 *  and if found desired sum it will add to output and backtrack and if not found desired sum, it will eventually return from (i == input.size) last index
 *  3. ANd then again with [2,2] sublist it will check with all next iterations with 4, 6, 8 and if found desired sum it will add to output and backtrack if not found desired sum, it will eventually return from (i == input.size) last index
 *  And then again after excluding, list will become [2],
 *  4. now with this [2] sublist it will check with all next iterations with 4, 6, 8 and if found desired sum it will add to output and backtrack
 *  and if not found desired sum, it will eventually return from (i == input.size) last index
 *
 *  And finally it will exclude 2,
 *  Then list will become empty, and now it will repeat same STEP 1 for 4, 6, 8 all elements
 */