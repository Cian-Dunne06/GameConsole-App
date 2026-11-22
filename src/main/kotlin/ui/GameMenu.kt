package ui

import service.GameService
import utils.readNextInt
import utils.readNextLine
import utils.readNextOptionalInt

class GameMenu(private val gameService: GameService) {

    fun start() {
        var option: Int
        do {
            option = showMenu()
            when (option) {
                1 -> addGame()
                2 -> listGames()
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
            ||  1) Add a game             |
            ||  2) List games             |
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

    private fun addGame() {
        val title = readNextLine("Title: ").trim()
        val version = readNextOptionalInt("Version (leave blank if none): ")
        val rating = readNextInt("Rating (0-100): ")
        val releaseYear = readNextInt("Release year: ")
        val genre = readNextLine("Genre: ").trim()

        try {
            val game = gameService.addGame(title, version, rating, releaseYear, genre)
            println("Game added:\n$game")
        } catch (e: IllegalArgumentException) {
            println("Game not added: ${e.message}")
        }
    }
}