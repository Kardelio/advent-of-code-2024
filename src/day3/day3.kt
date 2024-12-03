package day3

import utils.getFile

fun main() {
//    part1()
    part2()
}

fun splitToMul(input: String): Pair<Int, Int> {
    println("====${input}")
    val justNums = input.substringAfter("(").substringBefore(")")
    val values = justNums.split(",")
    println(values)
    return values[0].toInt() to values[1].toInt()
}

fun splitToMultipleMul(input: String): Int {
    println("++++++>${input}") //find all muls
    val out = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex().findAll(input)
    val b = out.map {
        val s = splitToMul(it.groupValues[0])
        s.first * s.second
    }.sum()
    return b
}

fun part1() {
    println("test")
    val data = getFile("src/day3/input.txt")
    println(data)
    val out = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex().findAll(data.joinToString(""))
    println(out.count())
    val b = out.map {
        val s = splitToMul(it.groupValues[0])
        s.first * s.second
    }.sum()
    println(b)
}

fun part2(){
    val data = getFile("src/day3/input.txt")
    val pre = """.+?(?=do|don\'t)""".toRegex().find(data.joinToString(""))
    var inc = 0
    pre?.let {
        println("---------")
        println("${it.groupValues[0]}")
        println("---------")
        val fi = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex().findAll(it.groupValues[0])
        fi?.let { init ->
            init.forEach { me ->
                val gg =  splitToMul(me.groupValues[0])
                inc += gg.first * gg.second
            }

        }
    }

    println("*************")
    println("*************")
    println("*************")

    val out = """do\(\)((?!don't\(\)).)*mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex().findAll(data.joinToString(""))
    val b = out.map {
        val sss =splitToMultipleMul(it.groupValues[0])
        println(sss)
        sss
    }.sum()
    println(b + inc)
}