package day5

import utils.getFile

var overallPages: List<Pair<Int, Int>>? = null

fun main() {
//    part1()
    part2()
}

fun getInstructions(data: List<String>): List<List<Int>> {
    val splitIndex = data.indexOf("")
    val instructions = data.subList(splitIndex + 1, data.size)
    return instructions.map {
        it.split(",").map { inner ->
            inner.toInt()
        }
    }
}

fun getPages(data: List<String>): List<Pair<Int, Int>> {
    val splitIndex = data.indexOf("")
    val pages = data.subList(0, splitIndex)
    println(pages)
    return pages.map {
        it.split("|")[0].toInt() to it.split("|")[1].toInt()
    }
}

val comparator = Comparator { o1: Int, o2: Int ->
    //0 if equal, - num if left is less than and + num if right greater than
    overallPages?.let { pages ->
        println("- ${o1} - ${o2}")
        val o1Pages = pages.filter { it.first == o1 }.map { it.second }
        val o2Pages = pages.filter { it.first == o2 }.map { it.second }
        if (o1Pages.contains(o2)) {
            println("1")
            -1
        } else if (o2Pages.contains(o1)) {
            println("-1")
            1
        } else {
            println("0")
            0
        }
    } ?: run {
        0
    }
}

fun fixRow(row: List<Int>, pages: List<Pair<Int, Int>>): List<Int> {
    val cache = mutableListOf<Int>()
    var out = mutableListOf<Int>()
    println("Sorted: ${row.sortedWith(comparator)}")
    return row.sortedWith(comparator)
//    println(":::::::")
//    for (indexInner in row.size - 1 downTo 0) {
//        val inner = row.getOrNull(indexInner)
//        val seconds = pages.filter { it.first == inner }.map { it.second }
//        println("1. ${inner}")
//
//        for (j in indexInner - 1 downTo 0) {
//            println("Check : ${row.getOrNull(j)}")
//            if (seconds.contains(row.get(j))) {
//                println("======= BAD ********** ${seconds}  ${row.getOrNull(j)}")
//                cache.add(row.get(indexInner))
//            } else {
//                out.add(row.get(indexInner))
//                if (cache.size > 0) {
//                    val removeIds = mutableListOf<Int>()
//                    cache.forEach { cac ->
//                        if (seconds.contains(cac)) {
//
//                        } else {
//                            out.add(cac)
//                            removeIds.add(cac)
//                        }
//                    }
//                    cache.removeAll(removeIds)
//                }
//                //ok
//                //61, 29, 13
//            }
//        }
//    }
//    println("Cache ----- ")
//    println(cache)
//    return out.reversed()
}

fun part2() {
//    val data = getFile("src/day5/test.txt")
    val data = getFile("src/day5/input.txt")
    val pages = getPages(data)
    overallPages = pages
    val instructions = getInstructions(data)

    var sum = 0
    val cleanedRows = mutableListOf<List<Int>>()
    instructions.forEachIndexed { index, outer ->
        println("==========")
        var rowOk = true
        for (indexInner in outer.size - 1 downTo 0) {
            val inner = outer.getOrNull(indexInner)
            println("=${indexInner} = ${outer.getOrNull(indexInner)}")
            println(inner)
            val seconds = pages.filter { it.first == inner }.map { it.second }

            println(pages.filter { it.first == inner })
            var ok = true
            for (j in indexInner - 1 downTo 0) {
                println("........ ${outer.getOrNull(j)}")
                //if number in seconds thats BAD
                //else pass
                if (seconds.contains(outer.get(j))) {
                    println("BAD ********** ${seconds} ${outer.get(j)} @ ${outer.getOrNull(j)}")
                    ok = false
                    break
                } else {
                    continue
                    //ok
                }
            }
            if (!ok) {
                rowOk = false
            } else {
                //here!
                //fix row...
//                val cleanRow = fixRow(outer, pages)
//                cleanedRows.add(cleanRow)
//                println("Clean row: ${cleanRow}, dirty row: ${outer}")
            }

//            //
//            val s = outer.subList(indexInner + 1, outer.size)
//            println("------")
//            for(ii in (indexInner + 1)..outer.size - 1){
//                println("+++++")
//
//                println(outer.getOrNull(ii))
//            }

        }
        if (rowOk) {
            //get middle elements
//            println(outer[outer.size / 2])
//            sum += outer[outer.size / 2]
        }else{
            val cleanRow = fixRow(outer, pages)
            cleanedRows.add(cleanRow)
        }
//        outer.forEachIndexed { indexInner, inner ->
//            //---
//            println(inner)
//            val seconds = pages.filter { it.first == inner }.map { it.second }
//
//            println(pages.filter { it.first == inner })
//
//            //
//            val s = outer.subList(indexInner + 1, outer.size)
//            println("------")
//            for(ii in (indexInner + 1)..outer.size - 1){
//                println("+++++")
//
//                println(outer.getOrNull(ii))
//            }
//
//        }
    }

    println("Cleaned row: ${cleanedRows.size}")
    cleanedRows.forEach {
//        println(it[it.size / 2])
        sum += it[it.size / 2]
    }

    println("Total: ${sum}")
}

fun part1() {

//    val data = getFile("src/day5/test.txt")
    val data = getFile("src/day5/input.txt")
    println(data)

    val splitIndex = data.indexOf("")
    println("split: ${splitIndex}")

    val pages = getPages(data)
    println(pages)

    val instructions = getInstructions(data)
    println("${instructions}")

    var sum = 0
    instructions.forEachIndexed { index, outer ->
        println("==========")
        println("==========")
        var rowOk = true
        for (indexInner in outer.size - 1 downTo 0) {
            val inner = outer.getOrNull(indexInner)
            println("=${indexInner} = ${outer.getOrNull(indexInner)}")
            println(inner)
            val seconds = pages.filter { it.first == inner }.map { it.second }

            println(pages.filter { it.first == inner })
            var ok = true
            for (j in indexInner - 1 downTo 0) {
                println("........ ${outer.getOrNull(j)}")
                //if number in seconds thats BAD
                //else pass
                if (seconds.contains(outer.get(j))) {
                    println("BAD ********** ${seconds} ${outer.get(j)} @ ${outer.getOrNull(j)}")
                    ok = false
                    break
                } else {
                    continue
                    //ok
                }
            }
            println("O0000000 OK! ${ok} - ${inner}")
            if (!ok) {
                rowOk = false
            }

//            //
//            val s = outer.subList(indexInner + 1, outer.size)
//            println("------")
//            for(ii in (indexInner + 1)..outer.size - 1){
//                println("+++++")
//
//                println(outer.getOrNull(ii))
//            }

        }
        println("This row: ${outer} == ${rowOk}")
        if (rowOk) {
            //get middle elements
            println(outer[outer.size / 2])
            sum += outer[outer.size / 2]
        }
//        outer.forEachIndexed { indexInner, inner ->
//            //---
//            println(inner)
//            val seconds = pages.filter { it.first == inner }.map { it.second }
//
//            println(pages.filter { it.first == inner })
//
//            //
//            val s = outer.subList(indexInner + 1, outer.size)
//            println("------")
//            for(ii in (indexInner + 1)..outer.size - 1){
//                println("+++++")
//
//                println(outer.getOrNull(ii))
//            }
//
//        }
    }
    println("Total: ${sum}")
}