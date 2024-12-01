package day1

import java.io.File
import kotlin.math.abs

fun main() {
    println("Start..")
    val lines = getFile()
    val listOne = mutableListOf<Int>()
    val listTwo = mutableListOf<Int>()
    lines.forEach {
        val splitt = it.split("   ")
        listOne.add(splitt[0].toInt())
        listTwo.add(splitt[1].toInt())
    }

    val done = listOne.map { orig ->
        val amount = listTwo.filter { it == orig }.count()
        orig * amount
    }.sum()

    println(done)
    //PART 1
//    println(listOne.sorted())
//    println(listTwo.sorted())
//    val done = listOne.sorted().zip(listTwo.sorted()).map {
//        println("${it.first} - ${it.second}")
//        val diff = it.first - it.second
//        println(abs(diff))
//        abs(diff)
//    }.sum()
//    println(done)


}
fun getFile(): List<String>{
    return File("src/day1/input.txt").readLines()
}