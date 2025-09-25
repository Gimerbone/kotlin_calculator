package com.example.calculator

class Utils {
    fun convertToBehindComma(n: Double): Double {
        result = n

        while (result >= 1.0) {
            result = result / 10
        }

        return result
    }
}