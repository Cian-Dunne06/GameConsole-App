package service

import model.Console

class ConsoleService {

    private val consoles = ArrayList<Console>()
    private var nextId = 0

    fun addConsole(name: String, company: String, releaseYear: Int, cost: Double, isSupported: Boolean): Console {
        val console = Console(nextId, name, company, releaseYear, cost, isSupported)
        nextId++
        consoles.add(console)
        return console
    }

    fun getConsoles(): List<Console> = consoles

    fun findConsoleById(id: Int): Console? = consoles.find { it.consoleId == id }

    fun updateConsole(
        id: Int,
        name: String,
        company: String,
        releaseYear: Int,
        cost: Double,
        isSupported: Boolean
    ): Boolean {
        val index = consoles.indexOfFirst { it.consoleId == id }
        if (index == -1) return false
        consoles[index] = Console(id, name, company, releaseYear, cost, isSupported)
        return true
    }

    fun deleteConsole(id: Int): Boolean = consoles.removeIf { it.consoleId == id }
}