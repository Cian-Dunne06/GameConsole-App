package ui

import service.ConsoleService
import service.GameService
import utils.readNextInt

class MainMenu(gameService: GameService, consoleService: ConsoleService) {

    private val gameMenu = GameMenu(gameService)
    private val consoleMenu = ConsoleMenu(consoleService)

    fun start() {
        var option: Int
        do {
            option = showMenu()
            when (option) {
                1 -> gameMenu.start()
                2 -> consoleMenu.start()
                0 -> println("Exiting App")
                else -> println("Invalid option")
            }
        } while (option != 0)
    }

    private fun showMenu(): Int {
        println(
            """
            |------------------------------
            ||      Game Console App      |
            |------------------------------
            ||  1) Games                  |
            ||  2) Consoles
            |------------------------------
            ||  0) Exit                   |
            |------------------------------
            """.trimMargin(),
        )
        return readNextInt("==>> ")
    }
}