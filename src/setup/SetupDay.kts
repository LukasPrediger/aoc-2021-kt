package setup

import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

// This script requires the advent of code cli to be installed and functional: https://github.com/scarvalhojr/aoc-cli

if (args.isEmpty()) {
    println("No day was passed")
    exitProcess(1)
}

val day = args[0]

val dayPadded = day.padStart(2, '0')

val outputFile = File("src/Day$dayPadded.kt")

println("Writing template to ${outputFile.path}")

if (outputFile.exists()) {
    println("Code file ${outputFile.path} already exists. Skipping...")
} else {
    val codeFileText = File("src/setup/DayX.kt.template").readText()
        .replace("DayX", "Day$dayPadded")

    outputFile.createNewFile()
    outputFile.writeText(codeFileText)
}

println()
println("Download input")
val inputFilePath = "src/Day${dayPadded}.txt"
val testFilePath = "src/Day${dayPadded}_test.txt"

if (File(inputFilePath).exists()) {
    println("Input file $inputFilePath already exists. Skipping...")
} else {
    ProcessBuilder(
        "aoc",
        "-d",
        day,
        "-f",
        inputFilePath,
        "d"
    ).redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start().waitFor(1, TimeUnit.MINUTES)
}

if (!File(testFilePath).createNewFile()) {
    println("Test file $inputFilePath already exists. Skipping...")
}

ProcessBuilder(
    "google-chrome",
    "https://adventofcode.com/2021/day/${day}"
).start()