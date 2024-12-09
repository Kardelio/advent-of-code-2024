package day9

import utils.getFile

fun main() {
//    val data = getFile("src/day9/input.txt")
    //NOT 45253
    //CORRECT: 6346871685398
    val data = getFile("src/day9/test.txt")
//    val data = getFile("src/day9/basic.txt")
    //1928
//    part1(process(data))
    part2(process(data))
}

data class Item(
    var number: Long? = null
)

data class ItemSecond(
    var number: Long? = null,
    var amount: Int = 0
)

fun process(data: List<String>): List<Int> {
    return data[0].map { it.toString().toInt() }
}

fun part1(data: List<Int>) {
    println(data)

    val builder = StringBuilder()

    val itemList: MutableList<Item> = mutableListOf()

    data.forEachIndexed { index, i ->
        if (index % 2 == 0) {
//            println(i) //0 first
            for (jj in 0..i - 1) {
                builder.append("{${index / 2}}")
                itemList.add(Item((index / 2).toLong()))
            }
        } else {
//            println("ODD: ${index} : ${i}")
            for (ii in 0..i - 1) {
                itemList.add(Item(null))
                builder.append(".")
            }
        }
    }

    val str = builder.toString()

//    println(data.joinToString(""))
    //COORECT NOW
//    println(str)
//    println("00...111...2...333.44.5555.6666.777.888899")
//    var count = str.length - 1
    var count = itemList.size - 1
    var altered = 0
    var freeAtStart = itemList.count { it.number == null }
    var done = false
    println("Length: ${count}")
    println("Free: ${freeAtStart}")
    val freshStrBuild = StringBuilder()


//    var sorted = itemList.
    var sorted = itemList.toMutableList()
//    println(sorted.indices)
//    println(sorted.indices)
//    println(sorted.indices)
//    println(str)
//    println(itemList)
    var alt = 0
    for (char in itemList.size - 1 downTo 0) {
//        println("${char} -> ${str[char]}")
        if (itemList[char].number != null) {

            for (j in sorted.indices) {
//                println("Sorted space: ${sorted[j]}")
//                println("Sorted num: ${sorted[j].number}")
                if (sorted[j].number == null) {
                    //found space
//                    println("FOUND SPACE")
                    sorted[j].number = itemList[char].number
                    alt++
//                    sorted[char] = '.'
//                    println(sorted)
                    break
                }
            }
        }
    }
    for (char in itemList.size - 1 downTo itemList.size - alt) {
        sorted[char].number = null
    }


    val freshStr = freshStrBuild.toString()
    println(freshStr)
    println(itemList)
    println("-----MINE-----")
    println(sorted.map { it.number }.joinToString(""))
    println(freshStr)
    println("0099811188827773336446555566..............")
    println("022111222......")

//    sorted.forEach {
//        println(it)
//    }
    val out = sorted.filter { it.number != null }.mapIndexed { index, c ->
        c.number!! * index
    }.sum()
    println(out)

    /*
    visualise
    sort
    sum
     */
}

fun part2(data: List<Int>) {
    println(data)

    val builder = StringBuilder()

    val itemList: MutableList<Item> = mutableListOf()

    data.forEachIndexed { index, i ->
        if (index % 2 == 0) {
//            println(i) //0 first
            for (jj in 0..i - 1) {
                builder.append("{${index / 2}}")
                itemList.add(Item((index / 2).toLong()))
            }
        } else {
//            println("ODD: ${index} : ${i}")
            for (ii in 0..i - 1) {
                itemList.add(Item(null))
                builder.append(".")
            }
        }
    }

    val str = builder.toString()

//    println(data.joinToString(""))
    //COORECT NOW
//    println(str)
//    println("00...111...2...333.44.5555.6666.777.888899")
//    var count = str.length - 1
    var count = itemList.size - 1
    var altered = 0
    var freeAtStart = itemList.count { it.number == null }
    var done = false
    println("Length: ${count}")
    println("Free: ${freeAtStart}")
    val freshStrBuild = StringBuilder()

    var sorted = itemList.toMutableList()

    println(itemList.map { if (it.number != null) it.number else '.' }.joinToString(""))


    var index = 0
    while (index < itemList.indices.last) {
        println(itemList[index].number)
        if (itemList[index].number == null) {
            //find out how big?
            var contcount = index
            var nextChar: Long? = null
            var space = 0
            while (nextChar == null) {
                contcount++
                nextChar = itemList[contcount].number //first is me
                println("--- ${nextChar}")
                if (nextChar == null) {
                    space++
                }
            }
            space++
            println("How many more balnks?: ${space} ")
            for (char in itemList.size - 1 downTo 0) {
                println("_____")
                println(itemList[char].number)
                if (itemList[char] != null) {
                    var valueCounter = char
                    var nextCharValue: Long? = itemList[char].number
                    var groupSize = 0
                    println("nextCharValue: ${nextCharValue}")
                    println("valueCounter: ${valueCounter}")
                    while (nextCharValue == itemList[char].number && valueCounter > 0) {
                        valueCounter--
                        nextCharValue = itemList[valueCounter].number //first is me
                        println("--- ${nextCharValue}")
                        if (nextCharValue == itemList[char].number) {
                            groupSize++
                        }
                    }
                    groupSize++
                    println("group size of... ${groupSize}")
                    if (groupSize <= space) {
                        println("We have a new home!!")
                        println("We have a new home!! for ${itemList[char].number}")
                        for (t in 0..groupSize) {
                            sorted[index + t].number = itemList[char].number
                        }
                        for(y in space downTo 0){
                            sorted[char - y].number = null
                        }
                        index += groupSize
//                        sorted[index] = itemList[index]
                        break
                    }
                }
            }

        } else {
            sorted[index] = itemList[index]
            index++
        }
        println(sorted.map { if (it.number != null) it.number else '.' }.joinToString(""))
//        index++
    }
}