package dsa.stack

import java.util.Stack

class EvaluatePostfixExpression {
}

fun evaluationOfPostfix(postfix: String): Int {
    val stack = Stack<Int>()

    for(c in postfix) {
        //if operand(digit) , use digitToInt to convert into int and then push to stack
        if(c.isDigit()) {
            //stack.push(c.toInt() - '0'.toInt())
            stack.push(c.code - '0'.code)
        } else {
            //if operator, take 2 operands and evealuate them, and push result to stack
            if(stack.size >= 2) {
                val value1 = stack.pop();
                val value2 = stack.pop();
                when(c) {
                    '+' -> stack.push(value2 + value1)
                    '-' -> stack.push(value2 - value1)
                    '*' -> stack.push(value2 * value1)
                    '/' -> stack.push(value2 / value1)
                }
            }

        }
    }
    return stack.pop()
}

fun main() {
    //val input = "a+b*(c^d-e)^(f+g*h)-i"
    val input = "231*+9-"
    //val input = "1-(-2)" //will not work for this case of unary minus operator
    val result = evaluationOfPostfix(input)
    println(result)

}

/**
 * It will only work for single digit operands, but not for multi digit, and also not for unary minus
 */