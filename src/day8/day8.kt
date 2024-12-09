package day8

import utils.getFile

data class Coord(
    val x: Int,
    val y: Int
)

enum class Direction(val x: Int, val y: Int) {
    TOP_LEFT(-1, -1),
    TOP_MID(0, -1),
    TOP_RIGHT(1, -1),
    MID_LEFT(-1, 0),
    MID_RIGHT(1, 0),
    BOTTOM_LEFT(-1, 1),
    BOTTOM_MID(0, 1),
    BOTTOM_RIGHT(1, 1)
}

fun main() {
//    val data = getFile("src/day8/test.txt")
    val data = getFile("src/day8/input.txt")
    /*
    ......#....#
    ...#....0...
    ....#0....#.
    ..#....0....
    ....0....#..
    .#....A.....
    ...#........
    #......#....
    ........A...
    .........A..
    ..........#.
    ..........#.
     */
    println(data)
//    part1(data)
    part2(data)
}

fun checkTillEdge(direction: Direction, findChar: Char, coordsNow: Pair<Int, Int>) {
    map?.let {
        var x = coordsNow.first
        var y = coordsNow.second
        while (x > -1 && x < mapWidth && y > -1 && y < mapHeight) {
            println("dir ${direction} => x: ${x}, y: ${y}")
            val charNow = it[y][x]
//            println("Found: ${charNow}")

            if(charNow == findChar && "${x},${y}" != "${coordsNow.first},${coordsNow.second}"){
                println("FOUNNNNDDD!! ${charNow} at ${x},${y}")
                //gap between add same over..
                println("diff: ${coordsNow.second - y} and ${coordsNow.first - x}")
                println("newx: ${x - (coordsNow.first - x)}")
                println("newy: ${y - (coordsNow.second - y)}")
                antiNodeMap[y - (coordsNow.second - y)][x - (coordsNow.first - x)] = '#'
            }

            x = x + direction.x
            y = y + direction.y
        }
    }
}

fun checkEverywhereElse(findChar: Char, coordsNow: Pair<Int, Int>){
    map?.let { mapp ->
        mapp.indices.forEach { y ->
            mapp[y].indices.forEach { x ->
//                println(mapp[y][x])
                if("${x},${y}" != "${coordsNow.first},${coordsNow.second}" && mapp[y][x] == findChar){
//                    println("FROM: ${coordsNow.first}, ${coordsNow.second} => HERE!!! ${findChar} at ${x},${y}")
                    val vecX = coordsNow.first - x
                    val vecY = coordsNow.second - y
//                    println("DIF: ${vecX}:${vecY}")
                    val newAntiX = x - (vecX)
                    val newAntiY = y - (vecY)
                    if(newAntiY > -1 && newAntiY < mapHeight && newAntiX > -1 && newAntiX < mapWidth){

                        antiNodeMap[newAntiY][newAntiX] = '#'
                    }
//                    antiNodeMap[newAntiY][newAntiX] = '#'
                }
            }
        }
    }
}


fun checkEverywhereElsePart2(findChar: Char, coordsNow: Pair<Int, Int>){
    map?.let { mapp ->
        mapp.indices.forEach { y ->
            mapp[y].indices.forEach { x ->
                if("${x},${y}" != "${coordsNow.first},${coordsNow.second}" && mapp[y][x] == findChar){
                    val vecX = coordsNow.first - x
                    val vecY = coordsNow.second - y
                    var newAntiX = x - (vecX)
                    var newAntiY = y - (vecY)
                    antiNodeMap[coordsNow.second][coordsNow.first] = '#'

                    while (newAntiY > -1 && newAntiY < mapHeight && newAntiX > -1 && newAntiX < mapWidth){
                        antiNodeMap[newAntiY][newAntiX] = '#'
                        newAntiX = newAntiX - vecX
                        newAntiY = newAntiY - vecY
                    }

//                    if(newAntiY > -1 && newAntiY < mapHeight && newAntiX > -1 && newAntiX < mapWidth){
//
//                        antiNodeMap[newAntiY][newAntiX] = '#'
//                    }
//                    antiNodeMap[newAntiY][newAntiX] = '#'
                }
            }
        }
    }
}

fun process(char: Char, currentX: Int, currentY: Int) {
//    println("Char: ${char} at {${currentX},${currentY}}")
    //scan out every direction
    //get delta amountk
    if (char != '.') {
//        checkEverywhereElse(char, currentX to currentY)
        checkEverywhereElsePart2(char, currentX to currentY)

//        Direction.values().forEach {
//            checkTillEdge(it, char,currentX to currentY)
//        }
    }
}

//val antiNodeMap: MutableList<MutableList<Char>> = MutableList(3, {})

var mapWidth = 0
var mapHeight = 0

var map: List<List<Char>>? = null
lateinit var antiNodeMap: MutableList<MutableList<Char>>

fun part2(data: List<String>) {
    map = data.map {
        it.map {
            it
        }
    }
    antiNodeMap = MutableList(data.size, { MutableList(data[0].length, { '.' }) })
    mapWidth = data[0].length
    mapHeight = data.size
    println("Widht: ${mapWidth} , maphegith: ${mapHeight}")

    for (y in data.indices) {
        for (x in data[y].indices) {
            process(data[y][x], x, y)
        }
    }
    val b =antiNodeMap.map {
        it.map {
            it
        }.joinToString("")
    }.joinToString("")
    println(b.count { it == '#' })
    printMapNew(antiNodeMap)
}

//PART 1
fun part1(data: List<String>) {
    /*
    Loops trough map
    if dot... nothing
    if symbol begin scan...
     */

    map = data.map {
        it.map {
            it
        }
    }
    antiNodeMap = MutableList(data.size, { MutableList(data[0].length, { '.' }) })
    mapWidth = data[0].length
    mapHeight = data.size
    println("Widht: ${mapWidth} , maphegith: ${mapHeight}")
//    println(antiNodeMap)

    for (y in data.indices) {
        for (x in data[y].indices) {
            process(data[y][x], x, y)
        }
    }
//    println(antiNodeMap)
    val b =antiNodeMap.map {
        it.map {
            it
        }.joinToString("")
    }.joinToString("")
    println(b.count { it == '#' })
//    printMapNew(antiNodeMap)
}

fun printMapNew(map: MutableList<MutableList<Char>>) {
    map.forEach {
        it.forEach {
            print("${it}")
        }
        print("\n")
    }
}