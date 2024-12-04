package day4

import utils.getFile

fun main() {
    part1()
}

enum class Direction(val coords: String) {
    TOP_LEFT("-1,-1"),
    TOP_MID("0,-1"),
    TOP_RIGHT("1,-1"),
    MID_LEFT("-1,0"),
    MID_RIGHT("1,0"),
    BOTTOM_LEFT("-1,1"),
    BOTTOM_MID("0,1"),
    BOTTOM_RIGHT("1,1")
}

fun checkAroundLetter(
    letterNow: Char,
    currentX: Int,
    currentY: Int,
    runningDirection: Direction?,
    allData: List<String>
): Boolean {
    val letterToFind = when (letterNow) {
        'X' -> 'M'
        'M' -> 'A'
        'A' -> 'S'
        'S' -> '_'
        else -> '.'
    }
    println("from : ${letterNow} -> ${letterToFind} => going: ${runningDirection}")
    //above
    if (letterToFind == '_') {
        //add point?
        return true
    } else if (letterToFind == '.') {
        return false
    } else {
        var result = false
        for (xx in -1..1) {
            for (yy in -1..1) {
                val newX = xx
                val newY = yy
                val currentDir = Direction.entries.find { it.coords == "${xx},${yy}" }
                println("{${currentX + newX},${currentY + newY}} = ${currentDir}")
                if (newX == 0 && newY == 0) {
                    //skipallData.getOrNull(currentY - 1)?.getOrNull(currentX) == letterToFind
                    continue
                } else {
                    //TODO direction needs to stay the same!
                    if (allData.getOrNull(currentY + newY)?.getOrNull(currentX + newX) == letterToFind && (runningDirection == null || runningDirection == currentDir)) {
                        println("Found letter: ${letterToFind} at {${currentX + newX},${currentY + newY}}")
                        result = checkAroundLetter(
                            letterToFind,
                            currentX + newX,
                            currentY + newY,
                            currentDir,
                            allData
                        )
//                        if(ans == true){
//                            result = ans
//                        }
                    }
//                    if(checkAroundLetter(letterToFind, xx, yy, allData) == true)
                }
            }
        }
        return result
    }
//    return false
}

fun part1() {
    val data = getFile("src/day4/smaller-input.txt")
//    val data = getFile("src/day4/input.txt")
    println(data)
    var sum = 0
    data.forEachIndexed { y, s ->
        println(s)
        s.forEachIndexed { x, c ->
            println(c)

            if (c == 'X') {
                println("----- {${x},${y}}")
                if (checkAroundLetter(c, x, y, null, data)) {
                    println("At {${x},${y}} X SUCCESS")
                    sum++
                } else {
                    println("At {${x},${y}} X failed")
                }
            }


        }
    }
    println(sum)
}

/*
NOT:
3658
3326
 */