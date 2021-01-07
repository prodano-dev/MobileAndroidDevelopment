package com.example.madlevel2task2

data class Question(

    var question: String,
    var correctAnswer: Boolean
) {
    companion object {
        val QUESTIONS = arrayOf(
            "A 'val' and var are the same.",
            "Mobile Application Development grants 12 ECTS.",
            "An unit in Kotlin cooresponds to a void in Java",
            "In Kotlin 'when' replaces the 'switch' operator in Java",
            "iOS development is more fun than Android dev."
        )

        val BOOLEANS = arrayOf(
            false,
            false,
            true,
            true,
            true
        )
    }
}