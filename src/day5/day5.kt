package day5

import utils.getFile


fun main() {
    part1()
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

fun getPages(data: List<String>): List<Pair<Int,Int>> {
    val splitIndex = data.indexOf("")
    val pages = data.subList(0, splitIndex)
    println(pages)
    return  pages.map {
        it.split("|")[0].toInt() to it.split("|")[1].toInt()
    }

//    return instructions.map {
//        it.split(",").map { inner ->
//            inner.toInt()
//        }
//    }
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
        for(indexInner in outer.size - 1 downTo 0){
            val inner = outer.getOrNull(indexInner)
            println("=${indexInner} = ${outer.getOrNull(indexInner)}")
            println(inner)
            val seconds = pages.filter { it.first == inner }.map { it.second }

            println(pages.filter { it.first == inner })
            var ok = true
            for(j in indexInner - 1 downTo 0){
                println("........ ${outer.getOrNull(j)}")
                //if number in seconds thats BAD
                //else pass
                if(seconds.contains(outer.get(j))){
                    println("BAD ********** ${seconds} ${outer.get(j)} @ ${outer.getOrNull(j)}")
                    ok = false
                    break
                }else {
                    continue
                    //ok
                }
            }
            println("O0000000 OK! ${ok} - ${inner}")
            if(!ok){
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
        if(rowOk){
            //get middle elements
            println(outer[outer.size/2])
            sum += outer[outer.size /2 ]
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