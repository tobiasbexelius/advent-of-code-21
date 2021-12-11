package day01

import common.InputRepo
import common.readSessionCookie
import common.solve

fun main(args: Array<String>) {
    val day = 1
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay01Part1, ::solveDay01Part2)
}

fun solveDay01Part1(input: List<String>): Int {
    val (count, _) = input.asSequence()
        .map { it.toInt() }
        .fold(0 to Int.MAX_VALUE) { (count, prevDepth), depth ->
            val newCount = if (depth > prevDepth) count + 1 else count
            newCount to depth
        }
    return count
}

fun solveDay01Part2(input: List<String>): Int {
    val (count, _) = input.asSequence()
        .map { it.toInt() }
        .windowed(size = 3) { it.sum() }
        .fold(0 to Int.MAX_VALUE) { (count, prevDepth), depth ->
            val newCount = if (depth > prevDepth) count + 1 else count
            newCount to depth
        }
    return count
}
