package utils

import java.io.File

fun getFile(path: String): List<String> {
    return File(path).readLines()
}