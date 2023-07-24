package algorithms.sorting

import java.util.*

fun radixSort(a: IntArray, n: Int) {
    //int max = getMax(a);
    val max = a.max()

    //for each digit of the largest element, repeat counting sort
    var pos = 1
    while (max / pos > 0) {
        countingSortImpl(a, n, pos)
        pos *= 10
    }
}

fun countingSortImpl(a: IntArray, n: Int, pos: Int) {
    val k = 9
    val count = IntArray(k + 1)
    val b = IntArray(n)

    //step1, freq counting
    for (i in 0 until n) {
        ++count[a[i] / pos % 10] //for digit at pos, for example, at 1's, 10's and 100's place
    }

    //step2, position calculation
    for (i in 1..k) {
        count[i] = count[i - 1] + count[i]
    }

    //step3, copy sorted array in new array
    for (i in n - 1 downTo 0) {
        b[--count[a[i] / pos % 10]] = a[i]
    }

    //step4, copy array back
    for (i in 0 until n) {
        a[i] = b[i]
    }
}

fun main(args: Array<String>) {
    val a = intArrayOf(2, 6, 4, 1, 9, 5, 8, 3, 5)
    radixSort(a, a.size)
    println(a.contentToString())
}

fun getMax(a: IntArray): Int {
    var max = a[0]
    for (i in 1 until a.size) {
        if (a[i] > max) {
            max = a[i]
        }
    }
    return max
}



/**
O(d*(n+k)) , where n is number of elements, and k is 10, and d is number of digits in largest element

Time and Space Complexity:
https://www.simplilearn.com/tutorials/data-structure-tutorial/radix-sort#:~:text=In%20radix%20sort%2C%20the%20worst,(n2).


Worst-Case Time Complexity -> O(n*n) if d = n
In radix sort, the worst case is when all elements have the same number of digits except one, which has a significantly large
number of digits. If the number of digits d in the largest element equals n, the runtime is O(n*n). => O(d*(n+k)) => O(n*(n+k)),
because k is constant => O(n*n)
If d is equal to n, then worst case complexity will be O(n*n) , but this will be very rare case, it can happen for
small number of elements less than 20, because maximum value of long type can be 9,223,372,036,854,775,807,
and it has 19 digits, and it means largest number we can have is this Long.MAX_VALUE.
Otherwise if n(array size) will be more than 20 , then d cant be equal to n

If d is not equal to n, then worst case complexity will be O(d*n)

Best Case Time Complexity -> O(d*n)
When all elements have the same number of digits, the best-case scenario occurs. O(d(n+k)) is the best-case time complexity.
If k equals O(1), the time complexity is O. (d*n).

Average Case Time Complexity -> O(d*(n+k))
You considered the distribution of the number of digits in the average case. There are 'd' passes or digits,
and each digit can have up to 'k' different values. Because radix sort is independent of the input sequence, we can keep n constant.

T(n) = d(n+k) is the running time of radix sort. Using the linearity of expectation and taking into account both sides' expectations.

Radix sort has an average case time complexity of O(d*(n+k)).


 */
