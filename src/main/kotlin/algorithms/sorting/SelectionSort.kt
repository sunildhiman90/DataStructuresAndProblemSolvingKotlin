package algorithms.sorting

fun main() {
    println("** Selection Sort **")
    println("Enter array elements with comma after each element " +
            "and then press enter:")
    val arr = readLine()!!.split(",").map { it.toInt() }
    val result = arr.selectionSort()
    println("Sorter array is : $result")
}

fun List<Int>.selectionSort(): List<Int> {
    var list = this.toMutableList()
    for (i in 0 until list.size) {
        var min = i
        for (j in i+1 until list.size) {
            if(list[j] < list[min]) {
                min = j
            }
        }
        //Only do swapping if min has changed,
        // for example if array is already sorted in asc order,
        // then min will not change
        if(min != i) {
            val temp = list[i]
            list[i] = list[min]
            list[min] = temp
        }
    }
    return list
}