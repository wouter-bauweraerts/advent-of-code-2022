package be.thebeehive.wouterbauweraerts.common

import java.io.File

fun readFromFile(path: String): String {
    return File(ClassLoader.getSystemResource(path).file).readText(Charsets.UTF_8)
}