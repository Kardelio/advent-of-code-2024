package day2

import utils.getFile

fun main() {
//    part1()
    part2()
//    part1Cleaner()
}

private fun isLineSafe(line: List<Int>): Boolean{
    println(line)
    if (line.size > 1) {
        val ascending = if (line[0] > line[1]) {
            false
        } else if (line[0] < line[1]) {
            true
        } else {
            return false
        }
        for (j in line.indices) {
            if (j == line.indices.last) {
                return true
            } else {
                if (ascending && line[j] < line[j + 1] && line[j + 1] - line[j] < 4) {
                } else if (!ascending && line[j] > line[j + 1] && line[j] - line[j + 1] < 4) {
                } else {
                    break
                }
            }
        }
        return false
    } else {
        return false
    }
//    var ok = false
//    for (jjj in spitt.indices) {
//        val freshList = spitt.toMutableList().apply { removeAt(jjj) }
//        println(spitt)
//        val amount = freshList.size
//        if (amount > 1) {
//            val ascending = if (freshList[0] > freshList[1]) {
//                false
//            } else if (freshList[0] < freshList[1]) {
//                true
//            } else {
//                continue
//            }
//            for (j in freshList.indices) {
//                if (j == freshList.indices.last) {
////                        counter++
//                    ok = true
//                    println("")
//                } else {
//                    if (ascending && freshList[j] < freshList[j + 1] && freshList[j + 1] - freshList[j] < 4) {
//                    } else if (!ascending && freshList[j] > freshList[j + 1] && freshList[j] - freshList[j + 1] < 4) {
//                    } else {
//                        ok = false
//                        break
//                    }
//                }
//            }
//        } else {
//            continue
//        }
//    }
//    if (ok == true) {
//        counter++
//    }
    return true
}

fun part2() {
    println("go")
    val data = getFile("src/day2/input.txt")
    var counter = 0
    for (item in data) {
        val spitt = item.split(" ").map { it.toInt() }
        var ok = false
        for(a in 0..spitt.lastIndex){
            ok = isLineSafe(spitt.toMutableList().apply { removeAt(a) })
            if (ok) break
        }
        if(ok){
            counter++
        }

    }
    println("Safe = ${counter}")
}

fun part1Cleaner(){
    println("go")
    val data = getFile("src/day2/input.txt")
    var counter = 0
    for (item in data) {
        if(isLineSafe(item.split(" ").map { it.toInt() })) counter++
    }
    println("Safe = ${counter}")
}

fun part1() {
    println("go")
    val data = getFile("src/day2/input.txt")
    var counter = 0
    for (item in data) {
        val spitt = item.split(" ").map { it.toInt() }
        val amount = spitt.size
        if (amount > 1) {
            val ascending = if (spitt[0] > spitt[1]) {
                false
            } else if (spitt[0] < spitt[1]) {
                true
            } else {
                continue
            }
            for (j in spitt.indices) {
                if (j == spitt.indices.last) {
                    counter++
                    println("")
                } else {
                    if (ascending && spitt[j] < spitt[j + 1] && spitt[j + 1] - spitt[j] < 4) {
                    } else if (!ascending && spitt[j] > spitt[j + 1] && spitt[j] - spitt[j + 1] < 4) {
                    } else {
                        break
                    }
                }
            }
        } else {
            continue
        }
    }
    println("Safe = ${counter}")
}