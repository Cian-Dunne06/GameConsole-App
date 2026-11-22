package utils

fun readNextInt(prompt: String = ""): Int {
    while (true) {
        print(prompt)

        val value = readln().trim().toIntOrNull()
        if (value != null) {
            return value
        }

        println("Please enter a valid integer.")
    }
}

fun readNextDouble(prompt: String = ""): Double {
    while (true) {
        print(prompt)

        val value = readln().trim().toDoubleOrNull()
        if (value != null) {
            return value
        }

        println("Please enter a valid double.")
    }
}

fun readNextFloat(prompt: String = ""): Float {
    while (true) {
        print(prompt)

        val value = readln().trim().toFloatOrNull()
        if (value != null) {
            return value
        }

        println("Please enter a valid float.")
    }
}

fun readNextLine(prompt: String = ""): String {
    print(prompt)
    return readln()
}

fun readNextChar(prompt: String = ""): Char {
    while (true) {
        print(prompt)
        val input = readln().trim()

        if (input.length == 1) {
            return input[0]
        }

        println("Please enter exactly one character.")
    }
}

fun readNextBoolean(prompt: String = ""): Boolean {
    print(prompt)
    return when (readlnOrNull()?.lowercase()) {
        "y", "yes", "true" -> true
        else -> false
    }
}

fun parseOptionalInt(text: String): Int? {
    val trimmed = text.trim()
    return if (trimmed.isEmpty()) null else trimmed.toInt()
}

fun readNextOptionalInt(prompt: String = ""): Int? {
    while (true) {
        print(prompt)
        try {
            return parseOptionalInt(readln())
        } catch (e: NumberFormatException) {
            println("Please enter a valid integer, or leave it blank.")
        }
    }
}
