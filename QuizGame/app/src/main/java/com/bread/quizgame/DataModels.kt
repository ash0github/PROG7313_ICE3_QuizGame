package com.bread.quizgame

data class Question(
    val questionText: String = "",
    val options: List<String> = listOf(),
    val correctAnswerIndex: Int = 0,
    val category: String = ""
)

data class Score(
    val userId: String = "testUser",
    val category: String = "",
    val score: Int = 0
)