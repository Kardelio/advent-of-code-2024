package day6

import utils.getFile

fun main() {
    part1()
}

enum class MOVEMENT(val symbol: Char, val xVel: Int, val yVel: Int, val turned: Char) {
    UP('^', 0, -1, '>'),
    DOWN('v', 0, 1, '<'),
    LEFT('<', -1, 0,'^'),
    RIGHT('>', 1, 0, 'v')
}

fun part1() {
//    val data = getFile("src/day6/test.txt")
    val data = getFile("src/day6/input.txt")
    println(data)

    val moddedMap = data.map { outer ->
        outer.map { inner ->
            inner
        }.toMutableList()
    }.toMutableList()

    var guardStart: Pair<Int, Int>? = null
    wrapper@ for (index in data.indices) {
        for (inner in data[index].indices) {
            if (data[index][inner] == '^') {
                guardStart = inner to index
                break@wrapper
            }
        }
    }
    println("Guard at ${guardStart}")
    guardStart?.let {
        moveGuard(it.first, it.second, moddedMap)
    }
}

fun printMap(map: MutableList<MutableList<Char>>){
    map.forEach {
        it.forEach {
            print("${it}")
        }
        print("\n")
    }
}

var touchedPoints : MutableList<Pair<Int, Int>> = mutableListOf()

fun moveGuard(currentX: Int, currentY: Int, map: MutableList<MutableList<Char>>) {
//    printMap(map)
    val currentSymbol = map.get(currentY).get(currentX)
    println(currentSymbol)
    val foundMovement = MOVEMENT.values().firstOrNull { it.symbol == currentSymbol }
    foundMovement?.let {
        val addX = it.xVel
        val addY = it.yVel
        val newCoords = (currentX + addX) to (currentY + addY)
        println(newCoords)
        try{
            val presentSymbol = map.get(newCoords.second).get(newCoords.first)
            println("---> ${presentSymbol}")
            if (presentSymbol == '.') {
                //ok
                map.get(newCoords.second).set(newCoords.first, currentSymbol)
                map.get(currentY).set(currentX, '.')
                touchedPoints.add(currentX to currentY)
                moveGuard(newCoords.first, newCoords.second, map)
            } else if (presentSymbol == '#') {
                //turn right
//            map.get(newCoords.second).set(newCoords.first, it.turned)
                map.get(currentY).set(currentX, it.turned)
                moveGuard(currentX, currentY, map)
            } else {
                //left map? //done!
            }
        } catch (e: Exception){
            println("GONE!")
            println(touchedPoints.distinct().size + 1)
        }


    }

}