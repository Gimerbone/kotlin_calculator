package com.example.calculator

class Utils {
    fun formatNumber(num: Double): String {
        return when {
            num.isNaN() || num.isInfinite() -> num.toString()
            num % 1.0 == 0.0 -> num.toLong().toString()
            else -> String.format("%.12g", num)
        }
    }
}