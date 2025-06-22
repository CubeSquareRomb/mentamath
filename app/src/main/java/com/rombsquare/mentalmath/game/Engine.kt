package com.rombsquare.mentalmath.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.random.Random

class Engine(val mode: String, val diff: String) {

    var task by mutableStateOf("<no task>")
    var answer by mutableIntStateOf(0)
    var solved by mutableIntStateOf(0)

    init {
        generateTask()
    }

    fun generateAdditionTask() {
        val (min, max) = when(diff) {
            "Easy" -> Pair(1, 20)
            "Medium" -> Pair(11, 100)
            "Hard" -> Pair(101, 1000)
            "Extreme" -> Pair(1001, 10000)
            else -> Pair(0, 1)
        }

        val a = Random.nextInt(min, max)
        val b = Random.nextInt(min, max)
        task = "$a+$b"
        answer = a+b
    }

    fun generateSubtractionTask() {
        val (min, max) = when(diff) {
            "Easy" -> Pair(1, 20)
            "Medium" -> Pair(11, 100)
            "Hard" -> Pair(101, 1000)
            "Extreme" -> Pair(1001, 10000)
            else -> Pair(0, 1)
        }

        val a = Random.nextInt(min, max+1)
        val b = Random.nextInt(min, max+1)

        val maxN = max(a, b)
        val minN = min(a, b)

        task = "$maxN-$minN"
        answer = maxN-minN
    }

    fun generateMultiplicationTask() {
        val (minA, maxA) = when(diff) {
            "Easy" -> Pair(2, 9)
            "Medium" -> Pair(11, 99)
            "Hard" -> Pair(11, 99)
            "Extreme" -> Pair(101, 999)
            else -> Pair(0, 1)
        }

        val (minB, maxB) = when(diff) {
            "Easy" -> Pair(2, 9)
            "Medium" -> Pair(2, 9)
            "Hard" -> Pair(11, 99)
            "Extreme" -> Pair(11, 99)
            else -> Pair(0, 1)
        }

        val a = Random.nextInt(minA, maxA+1)
        val b = Random.nextInt(minB, maxB+1)

        task = "$a×$b"
        answer = a*b
    }

    fun generateDivisionTask() {
        val (minA, maxA) = when(diff) {
            "Easy" -> Pair(1, 100)
            "Medium" -> Pair(101, 1000)
            "Hard" -> Pair(1001, 10000)
            "Extreme" -> Pair(10001, 100000)
            else -> Pair(0, 1)
        }

        val a = Random.nextInt(minA, maxA+1)

        val divisors = getDivisors(a)

        // If there are no divisors (the number is prime), regenerate the task
        if (divisors.isEmpty()) {
            return generateDivisionTask()
        }

        val b = divisors.random()

        task = "$a÷$b"
        answer = a/b
    }

    fun generateExponentTask() {
        val (minA, maxA) = when(diff) {
            "Easy" -> Pair(2, 2)
            "Medium" -> Pair(2, 9)
            "Hard" -> Pair(11, 99)
            "Extreme" -> Pair(11, 99)
            else -> Pair(0, 1)
        }

        val (minB, maxB) = when(diff) {
            "Easy" -> Pair(2, 16)
            "Medium" -> Pair(3, 4)
            "Hard" -> Pair(2, 2)
            "Extreme" -> Pair(3, 3)
            else -> Pair(0, 1)
        }

        val a = Random.nextInt(minA, maxA+1)
        val b = Random.nextInt(minB, maxB+1)

        task = "$a^$b"
        answer = a.toDouble().pow(b.toDouble()).toInt()
    }



    fun generateTask() {
        return when (mode) {
            "add" -> generateAdditionTask()
            "sub" -> generateSubtractionTask()
            "mul" -> generateMultiplicationTask()
            "div" -> generateDivisionTask()
            "exp" -> generateExponentTask()
            else -> throw IllegalArgumentException("Invalid mode: $mode")
        }
    }

    fun check(userAnswer: Int): Boolean {
        if (userAnswer != answer) {
            return false
        }

        generateTask()
        solved++
        return true
    }
}

fun getDivisors(n: Int): MutableList<Int> {
    val divisors = mutableListOf<Int>()
    for (i in 2..<n) {
        if (n % i == 0) {
            divisors.add(i)
        }
    }

    return divisors
}

fun main() {
    val engine = Engine("exp", "Extreme")

    engine.generateTask()

    println(engine.task)
    println(engine.answer)
}
