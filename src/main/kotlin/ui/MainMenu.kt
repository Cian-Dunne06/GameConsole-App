package ui

import service.GameService
import utils.readNextInt

class MainMenu(gameService: GameService) {

    private val gameMenu = GameMenu(gameService)

    fun start() {
        var option: Int
        do {
            option = showMenu()
            when (option) {
                1 -> gameMenu.start()
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
            |------------------------------
            ||  0) Exit                   |
            |------------------------------
            """.trimMargin(),
        )
        return readNextInt("==>> ")
    }
}