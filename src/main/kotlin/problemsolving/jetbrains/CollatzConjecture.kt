package problemsolving.jetbrains

class CollatzConjecture {
}

fun main() {
    val n = readLine()!!.toInt()
    val sequence = generateSequence(n) {
        if (it > 1) {
            if (it % 2 == 0) {
                it / 2
            } else {
                it * 3 + 1
            }
        } else null//sequence stop generating items when it returns null
    }
    println(sequence.toList())
}


/***
 * Collatz conjecture

You have a natural number n. Generate a sequence of integers, described in the Collatz conjecture (Wikipedia):

if n is an even number, divide it in half;
if it is odd, multiply it by 3 and add 1 ;
Repeat this operation until you get 1 as a result.

For example, if n is 17, then the sequence would look like this:

17 52 26 13 40 20 10 5 16 8 4 2 1
This sequence should stop at 1 for any primary natural number n.

Output format:
A sequence of integers in a single line, separated by spaces.

Sample Input 1:

17
Sample Output 1:

17 52 26 13 40 20 10 5 16 8 4 2 1
Sample Input 2:

1
Sample Output 2:

1

 */