package ui

import service.GameService
import utils.readNextInt

class GameMenu(private val gameService: GameService) {

    fun start() {
        var option: Int
        do {
            option = showMenu()
            when (option) {
                1 -> listGames()
                0 -> println("Exiting App")
                else -> println("Invalid option")
            }
        } while (option != 0)
    }

    private fun showMenu(): Int {
        println(
            """
            |------------------------------
            ||        Game Library        |
            |------------------------------
            ||  1) List games             |
            |------------------------------
            ||  0) Exit                   |
            |------------------------------
            """.trimMargin(),
        )
        return readNextInt("==>> ")
    }

    private fun listGames() {
        val games = gameService.getGames()
        if (games.isEmpty()) {
            println("No games stored")
        } else {
            games.forEach { println(it) }
            println("There are ${games.size} games")
        }
    }
}