package day02

import common.InputRepo
import common.readSessionCookie
import common.solve

fun main(args: Array<String>) {
    val day = 2
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay02Part1, ::solveDay02Part2)
}

fun solveDay02Part1(input: List<String>): Int =
    input.asSequence()
        .map { fullCmd ->
            val splitCmd = fullCmd.split(" ")
            splitCmd[0] to splitCmd[1].toInt()
        }
        .fold(0 to 0) { (d, h), (cmd, units) ->
            when (cmd) {
                "forward" -> d to h + units
                "up" -> d - units to h
                "down" -> d + units to h
                else -> throw IllegalArgumentException()
            }
        }
        .let { (d, h) -> d * h }

fun solveDay02Part2(input: List<String>): Int {
    data class Position(val d: Int, val h: Int, val aim: Int)
    return input.asSequence()
        .map { fullCmd ->
            val splitCmd = fullCmd.split(" ")
            splitCmd[0] to splitCmd[1].toInt()
        }
        .fold(Position(0, 0, 0)) { p, (cmd, units) ->
            when (cmd) {
                "forward" -> p.copy(d = p.d + p.aim * units, h = p.h + units)
                "up" -> p.copy(aim = p.aim - units)
                "down" -> p.copy(aim = p.aim + units)
                else -> throw IllegalArgumentException()
            }
        }
        .let { (d, h) -> d * h }
}
