package com.example.kalculator

class Calculator {
    private val numbers = mutableListOf<String>()
    private val operators = listOf("+", "/", "*", "-")

    fun isLastNumberOperator(): Boolean {
        return numbers.isNotEmpty() && operators.contains(numbers.last())
    }

    fun setNumber(value: String) {
        if (value.isNotEmpty()) { numbers.add(value) }
    }

    fun setOperator(value: String) {
        if (operators.contains(value)) { numbers.add(value) }
    }

    fun changeLastOperator(value: String) {
        if (isLastNumberOperator() && operators.contains(value)) {
            numbers[numbers.lastIndex] = value
        }
    }

    fun clr() {
        numbers.clear()
    }

    fun result(): String {
        var result = 0.0
        var currentOperator = ""
        numbers.forEach { value ->
            if (!operators.contains(value) && currentOperator.isEmpty()) {
                result = value.toDouble()
            } else if (currentOperator.isNotEmpty()) {
                result = calculate(currentOperator, result, value.toDouble())
                currentOperator = ""
            } else {
                currentOperator = value
            }
        }
        return result.toString()
    }

    fun getDigitHistory(): String { return numbers.joinToString(" ") }

    private fun calculate(operator: String, first: Double, second: Double): Double {
        return when (operator) {
            "+" -> (first + second)
            "-" -> (first - second)
            "*" -> (first * second)
            "/" -> (first / second)
            else -> 0.0
        }
    }
}
