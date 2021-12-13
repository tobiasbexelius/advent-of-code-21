package day03

import common.InputRepo
import common.readSessionCookie
import common.solve

fun main(args: Array<String>) {
    val day = 3
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay03Part1, ::solveDay03Part2)
}

fun solveDay03Part1(input: List<String>): Int {
    val wordSize = input.first().length
    return input.asSequence()
        .fold(IntArray(wordSize) { 0 }) { acc, s ->
            s.forEachIndexed { index, c ->
                if (c == '1') {
                    acc[index]++
                }
            }
            acc
        }
        .let { bitCount ->
            val gamma = bitCount
                .map { if (it >= input.size / 2) 1 else 0 }
                .joinToString(separator = "")
                .let { Integer.parseInt(it, 2) }
            val ypsilon = bitCount
                .map { if (it >= input.size / 2) 0 else 1 }
                .joinToString(separator = "")
                .let { Integer.parseInt(it, 2) }
            gamma * ypsilon
        }
}

fun solveDay03Part2(input: List<String>): Int {
    val wordSize = input.first().length
    var ogrItems = input
    var csrItems = input.toList()
    repeat(wordSize) { index ->
        ogrItems = ogrItems.partition { it[index] == '0' }
            .let { if (it.first.size > it.second.size) it.first else it.second }
        csrItems = csrItems.partition { it[index] == '0' }
            .let {
                if (
                    it.second.isEmpty() ||
                    (it.first.size <= it.second.size && it.first.isNotEmpty())
                ) it.first else it.second
            }
    }
    val ogr = ogrItems.single().let { Integer.parseInt(it, 2) }
    val csr = csrItems.single().let { Integer.parseInt(it, 2) }
    return ogr * csr
}
