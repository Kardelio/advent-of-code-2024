package day6

import utils.getFile

fun main() {
    part2Overall()
}
var places: MutableList<String> = mutableListOf()
var guardStart2Fresh: Pair<Int, Int>? = null
var overallMap : MutableList<MutableList<Char>>? = null
var amountOfColisions = 0
fun printMapNew(map: MutableList<MutableList<Char>>) {
    map.forEach {
        it.forEach {
            print("${it}")
        }
        print("\n")
    }
}
fun moveOverall(currentX: Int, currentY: Int){
    overallMap?.let { mapp ->
        val currentSymbol = mapp.get(currentY).get(currentX)
        println(currentSymbol)
        val foundMovement = MOVEMENT.values().firstOrNull { it.symbol == currentSymbol }
        foundMovement?.let {
            //find next block
            //change block
            //run move
            var nextBlock = 'x'
            var actualX = currentX + it.xVel
            var actualY = currentY + it.yVel
            try{
                nextBlock = mapp.get(actualY).get(actualX)
                println(nextBlock)
                if(nextBlock == '.'){
                    mapp.get(actualY).set(actualX, '#')
                    printMapNew(mapp)
                    if(moveWithFlux(currentX,currentY, "${currentX},${currentY}",currentSymbol)){
                        countOfObstructions++
                    }
                    mapp.get(actualY).set(actualX, '.')
//                    mapp.get(actualY).set(actualX, currentSymbol)
                    printMapNew(mapp)
                }
                println("AGAIN")

                moveOverall(actualX, actualY)
            }catch (e :Exception){
                println("FUUUUUUUUUUU")
            }
//            var approachingBlock = '.'
//            while (approachingBlock != '#' && approachingBlock != 'O'){
//                val addX = it.xVel
//                val addY = it.yVel
//                actualY = actualY + addY
//                actualX = actualX + addX
//                try {
//                    approachingBlock = mapp.get(actualY).get(actualX)
//                    println("Found: ${approachingBlock}")
//                    if(approachingBlock != '#'  && approachingBlock != 'O' ){
//                        places.add("${actualX},${actualY}")
//                    }
//                }catch(e: Exception){
//                    approachingBlock = 'O'
//                    break
//                }
//            }
//            println("Final was: ${approachingBlock} at {${actualX},${actualY}}")
//            if(approachingBlock == '#'){
//                amountOfColisions++
//                println("Rotate!")
//                mapp.get(actualY - it.yVel).set(actualX - it.xVel, it.turned)
//                moveWithFlux(actualX - it.xVel,actualY - it.yVel)
//            }else {
//                println("Guard was: ${places.distinct().size}")
//                println("Collisions: ${amountOfColisions}")
//            }
        }
    }
}

var countOfObstructions = 0

fun moveWithFlux(currentX: Int, currentY: Int, originalPos: String, originalIcon: Char) : Boolean{
    println(originalPos)
    println(originalIcon)
    overallMap?.let { mapp ->
        val currentSymbol = mapp.get(currentY).get(currentX)
        val foundMovement = MOVEMENT.values().firstOrNull { it.symbol == currentSymbol }
        foundMovement?.let {
            var actualX = currentX
            var actualY = currentY
            var approachingBlock = '.'
            while (approachingBlock != '#' && approachingBlock != 'O'){
                val addX = it.xVel
                val addY = it.yVel
                actualY = actualY + addY
                actualX = actualX + addX
                try {
                    approachingBlock = mapp.get(actualY).get(actualX)
                    println("Found: ${approachingBlock} at {${actualX},${actualY}}")
                    if(approachingBlock != '#'  && approachingBlock != 'O' ){
                        places.add("${actualX},${actualY}")
                    }
                }catch(e: Exception){
                    approachingBlock = 'O'
                    break
                }
            }
            println("Final was: ${approachingBlock} at {${actualX},${actualY}}")
            if(approachingBlock == '#'){
                amountOfColisions++
                println("Rotate!")
                mapp.get(actualY - it.yVel).set(actualX - it.xVel, it.turned)
                return moveWithFlux(actualX - it.xVel,actualY - it.yVel, "${actualX - it.xVel},${actualY - it.yVel}",currentSymbol)
            }else {
                println("Guard was: ${places.distinct().size}")
                println("Collisions: ${amountOfColisions}")
                return false
            }
        } ?: run {
            return false
        }
    } ?: run {
        return false
    }
}
//
//fun move(currentX: Int, currentY: Int){
//    overallMap?.let { mapp ->
//        val currentSymbol = mapp.get(currentY).get(currentX)
//        val foundMovement = MOVEMENT.values().firstOrNull { it.symbol == currentSymbol }
//        foundMovement?.let {
//            var actualX = currentX
//            var actualY = currentY
//            var approachingBlock = '.'
//            while (approachingBlock != '#' && approachingBlock != 'O'){
//                val addX = it.xVel
//                val addY = it.yVel
//                actualY = actualY + addY
//                actualX = actualX + addX
//                try {
//                    approachingBlock = mapp.get(actualY).get(actualX)
//                    println("Found: ${approachingBlock}")
//                    if(approachingBlock != '#'  && approachingBlock != 'O' ){
//                        places.add("${actualX},${actualY}")
//                    }
//                }catch(e: Exception){
//                    approachingBlock = 'O'
//                    break
//                }
//            }
//            println("Final was: ${approachingBlock} at {${actualX},${actualY}}")
//            if(approachingBlock == '#'){
//                println("Rotate!")
//                mapp.get(actualY - it.yVel).set(actualX - it.xVel, it.turned)
//                move(actualX - it.xVel,actualY - it.yVel)
//            }else {
//                println("Guard was: ${places.distinct().size}")
//            }
//        }
//    }
//}

fun part2Overall() {

    val data = getFile("src/day6/test.txt")
//    val data = getFile("src/day6/input.txt")

    val moddedMap = data.map { outer ->
        outer.map { inner ->
            inner
        }.toMutableList()
    }.toMutableList()
    overallMap = moddedMap

    var guardStart: Pair<Int, Int>? = null
    wrapper@ for (index in data.indices) {
        for (inner in data[index].indices) {
            if (data[index][inner] == '^') {
                guardStart2Fresh = inner to index
//                guardStart2 = inner to index
                break@wrapper
            }
        }
    }
    println("Guard at ${guardStart2Fresh}")

    guardStart2Fresh?.let {
        moveOverall(it.first, it.second)
    }
    println("countOfObstructions: ${countOfObstructions}")
}