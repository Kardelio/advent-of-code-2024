package day6

import utils.getFile

fun main() {
//    part1()
    part2()
}
var guardStart2: Pair<Int, Int>? = null
fun part2() {

//    val data = getFile("src/day6/test.txt")
    val data = getFile("src/day6/input.txt")
    //NOT 115 or 116
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
                guardStart2 = inner to index
                break@wrapper
            }
        }
    }
    println("Guard at ${guardStart}")

    guardStart?.let {
        moveGuardWithBlockers(it.first, it.second, moddedMap)
    }
}

//fun createloop(currentx: int, currentY: Int){
//   if()
//}
//

enum class MOVEMENT(val symbol: Char, val xVel: Int, val yVel: Int, val turned: Char) {
    UP('^', 0, -1, '>'),
    DOWN('v', 0, 1, '<'),
    LEFT('<', -1, 0, '^'),
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

fun printMap(map: MutableList<MutableList<Char>>) {
    map.forEach {
        it.forEach {
            print("${it}")
        }
        print("\n")
    }
}

var touchHistory = mutableListOf<Pair<Int,Int>>()
var loopCount = 0
//
//fun canCloseLoop(currentX: Int, currentY: Int, lastThree: List<Pair<Int,Int>>) :Boolean{
//    if(currentX < lastThree.first().first){
//        println("X past so think YES obsctuct")
//        return true
//    } else if (currentY < lastThree.first().second){
//        println("Y past so think YES obsctuct")
//        return true
//    }else{
//        return false
//    }
//
//}



fun moveGuardWithBlockers(currentX: Int, currentY: Int, map: MutableList<MutableList<Char>>) {
    val currentSymbol = map.get(currentY).get(currentX)
    val foundMovement = MOVEMENT.values().firstOrNull { it.symbol == currentSymbol }
    foundMovement?.let {
        val addX = it.xVel
        val addY = it.yVel
        val newCoords = (currentX + addX) to (currentY + addY)
        try {
            val presentSymbol = map.get(newCoords.second).get(newCoords.first)
            println("---> ${presentSymbol} at {${newCoords.first},${newCoords.second}}")
            if (presentSymbol == '#') {
                touchHistory.add(newCoords.first to newCoords.second)
//                if(touchHistory.size > 2){
//                    println(touchHistory)
//                    if(canCloseLoop(newCoords.first, newCoords.second, touchHistory.takeLast(3))){
//                        loopCount++
//                    }
//                }
                map.get(currentY).set(currentX, it.turned)
                moveGuardWithBlockers(currentX, currentY, map)
            } else {
                //Here... put block down and see if returns to this point? after next 3 turns
                //TODO
                println("Adding temp block #")
                map.get(newCoords.second).set(newCoords.first, '#')
                //run jfor 3
                currentMapHold = map
                if(boringMoveGuard(currentX, currentY, 0, "${newCoords.first},${newCoords.second}",foundMovement, touchHistory.size)){
                    println("ADDDDD OBSTRUCTION HERE: {${currentX},${currentY}}")
                    loopCount++
                }
//                map.get(newCoords.second).set(newCoords.first, '.')

                map.get(newCoords.second).set(newCoords.first, currentSymbol)
                map.get(currentY).set(currentX, '.')
                touchedPoints.add(currentX to currentY)
                moveGuardWithBlockers(newCoords.first, newCoords.second, map)
            }
        } catch (e: Exception) {
            println("GONE!")
            println("LoopCount: ${loopCount}")
            println(touchedPoints.distinct().size + 1)
        }
    }
}

/*
------------------------------------------
------------------------------------------
------------------------------------------
------------------------------------------
------------------------------------------
 */

var touchedPoints: MutableList<Pair<Int, Int>> = mutableListOf()
var currentMapHold : MutableList<MutableList<Char>> = mutableListOf()

fun boringMoveGuard(currentX: Int, currentY: Int, hits: Int, originalPoint: String, originalDirection: MOVEMENT, touchesTillNow: Int) :Boolean {
    val currentSymbol = currentMapHold.get(currentY).get(currentX)
println("boringMoveGuard: ${hits} (${currentSymbol}) mtn: ${touchesTillNow}")
    val foundMovement = MOVEMENT.values().firstOrNull { it.symbol == currentSymbol }
    foundMovement?.let {
        val addX = it.xVel
        val addY = it.yVel
        val newCoords = (currentX + addX) to (currentY + addY)
        try {
            val presentSymbol = currentMapHold.get(newCoords.second).get(newCoords.first)
            if (presentSymbol == '.') {
                //ok
                currentMapHold.get(newCoords.second).set(newCoords.first, currentSymbol)
                currentMapHold.get(currentY).set(currentX, '.')
//                touchedPoints.add(currentX to currentY)
               return boringMoveGuard(newCoords.first, newCoords.second, hits, originalPoint, originalDirection, touchesTillNow)
            } else if (presentSymbol == '#') {
                //turn right
//            map.get(newCoords.second).set(newCoords.first, it.turned)
                println("Hiot block at ${newCoords.first}, ${newCoords.second}")
                currentMapHold.get(currentY).set(currentX, it.turned)
                if("${newCoords.first},${newCoords.second}" == originalPoint && foundMovement == originalDirection && hits >= 4){
                    return true
                }
                else if(touchesTillNow < hits){
                    return false
                }else{
                    return boringMoveGuard(currentX, currentY, hits+1, originalPoint,originalDirection, touchesTillNow)
                }
//               return boringMoveGuard(currentX, currentY, map, hits+1, originalPoint,originalDirection)
            }

            else {
                //ok
                currentMapHold.get(newCoords.second).set(newCoords.first, currentSymbol)
                currentMapHold.get(currentY).set(currentX, '.')
//                touchedPoints.add(currentX to currentY)
                return boringMoveGuard(newCoords.first, newCoords.second, hits, originalPoint,originalDirection, touchesTillNow)
            }
        } catch (e: Exception) {
            println("OFF BOARD")
            return false

        }
    } ?: run  {
        return false
    }

}
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
        try {
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
        } catch (e: Exception) {
            println("GONE!")
            println(touchedPoints.distinct().size + 1)
        }


    }

}