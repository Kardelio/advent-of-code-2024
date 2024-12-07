package day7

import utils.getFile


fun main() {
    part1()
}

data class line(
    val result: Long,
    val items: List<Long>
)

fun convertToObjects(data: List<String>): List<line> {
    return data.map {
        line(it.substringBefore(":").toLong(), it.substringAfter(": ").split(" ").map { it.toLong() })
    }
}

enum class OPERATORS {
    PLUS,
    TIMES
}

fun calculateNumbers(operators: OPERATORS, value1: Long, value2: Long): Long {
    return when (operators) {
        OPERATORS.TIMES -> {
            value1 * value2
        }

        OPERATORS.PLUS -> {
            value1 + value2
        }
    }
}

//fun trigger(value1: Int, value2: Int, index: Int, following: List<Int>, mutableList: MutableList<Int>) : List<Int> {
//    OPERATORS.values().forEach {
//        val now = calculateNumbers(it, value1, value2)
//        trigger(now, following.get(index + 1), index + 1, following.subList(index + 1, following.size), )
//    }
//}

fun trig(startIndex: Int, remainingList: List<Long>, acc: Long, list: MutableList<Long>): List<Long>{
    println("-> ${acc} ${startIndex} : ${remainingList}")
    if(startIndex >= remainingList.size - 1){
        println("DONE")
        list.add(acc)
        return list
    }else{
        return OPERATORS.values().flatMap {
            val out = calculateNumbers(it, acc, remainingList[startIndex+1])
            println("trig: ${it} ${remainingList} = ${out}")
            println("Acc in ${acc}")
            trig(startIndex + 1, remainingList, out, list)
        }
    }
}


fun part1() {
    val data = getFile("src/day7/input.txt")
//    val data = getFile("src/day7/test.txt")
    //3749
    println(data)
    val converted = convertToObjects(data)
    var sum = 0L

    converted.forEach {
        println(it.result)
        println(it.items)
        println(it.items.windowed(2).toList())
        val a = trig(0, it.items, it.items[0], mutableListOf())
        println(a)
        if(a.contains(it.result)){
            println("CONTAINS RESULT ${it.items} => ${it.result}")
            sum += it.result
        }
        //2654749936343
    }
    println("SUM: ${sum}")
}