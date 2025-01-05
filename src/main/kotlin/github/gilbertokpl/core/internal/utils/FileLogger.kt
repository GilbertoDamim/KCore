package github.gilbertokpl.core.internal.utils

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FileLogger {

    private val logDir = File("plugins/TotalEssentials/log")
    private val logFile = File(logDir, "log.txt")

    init {
        if (!logDir.exists()) {
            logDir.mkdirs()
        }
        if (!logFile.exists()) {
            logFile.createNewFile()
        }
    }

    private fun getCurrentDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        return current.format(formatter)
    }

    fun log(message: String) {
        try {
            val writer = FileWriter(logFile, true)
            writer.appendLine("${getCurrentDateTime()} - $message")
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}