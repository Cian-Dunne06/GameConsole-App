package ui

import service.ConsoleService
import utils.readNextDouble
import utils.readNextInt
import utils.readNextLine
import utils.readNextYesNo

class ConsoleMenu(private val consoleService: ConsoleService) {

    fun start() {
        var option: Int
        do {
            option = showMenu()
            when (option) {
                1 -> addConsole()
                2 -> listConsoles()
                0 -> println("Returning to main menu")
                else -> println("Invalid option")
            }
        } while (option != 0)
    }

    private fun showMenu(): Int {
        println(
            """
            |------------------------------
            ||       Console Library      |
            |------------------------------
            ||  1) Add a console          |
            ||  2) List consoles          |
            |------------------------------
            ||  0) Back                   |
            |------------------------------
            """.trimMargin(),
        )
        return readNextInt("==>> ")
    }

    private fun readConsoleDetails() =
        ConsoleDetails(
            name = readNextLine("Name: ").trim(),
            company = readNextLine("Company: ").trim(),
            releaseYear = readNextInt("Release year: "),
            cost = readNextDouble("Cost: "),
            isSupported = readNextYesNo("Still supported? (y/n): "),
        )

    private fun addConsole() {
        val details = readConsoleDetails()

        try {
            val console = consoleService.addConsole(
                details.name, details.company, details.releaseYear, details.cost, details.isSupported
            )
            println("Console added:\n$console")
        } catch (e: IllegalArgumentException) {
            println("Console not added: ${e.message}")
        }
    }

    private fun listConsoles() {
        val consoles = consoleService.getConsoles()
        if (consoles.isEmpty()) {
            println("No consoles stored")
        } else {
            consoles.forEach { println(it) }
            println("There are ${consoles.size} consoles")
        }
    }
}

private data class ConsoleDetails(
    val name: String,
    val company: String,
    val releaseYear: Int,
    val cost: Double,
    val isSupported: Boolean,
)