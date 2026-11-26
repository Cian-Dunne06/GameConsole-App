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



fun readNextLine(prompt: String = ""): String {
    print(prompt)
    return readln()
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

fun parseYesNo(text: String): Boolean? =
    when (text.trim().lowercase()) {
        "y", "yes", "true" -> true
        "n", "no", "false" -> false
        else -> null
    }

fun readNextYesNo(prompt: String = ""): Boolean {
    while (true) {
        print(prompt)
        val answer = parseYesNo(readln())
        if (answer != null) {
            return answer
        }
        println("Please answer y or n.")
    }
}
