package ui

import service.GameService
import utils.readNextInt
import utils.readNextLine
import utils.readNextOptionalInt
import utils.readNextBoolean

private data class GameDetails(
    val title: String,
    val version: Int?,
    val rating: Int,
    val releaseYear: Int,
    val genre: String,
)

class GameMenu(private val gameService: GameService) {

    fun start() {
        var option: Int
        do {
            option = showMenu()
            when (option) {
                1 -> addGame()
                2 -> listGames()
                3 -> updateGame()
                4 -> deleteGame()
                0 -> println("Returning to main menu")
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
            ||  3) Update a game          |
            ||  4) Delete a game          |
            |------------------------------
            ||  0) Back                   |
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

    private fun readGameDetails() =
        GameDetails(
            title = readNextLine("Title: ").trim(),
            version = readNextOptionalInt("Version (leave blank if none): "),
            rating = readNextInt("Rating (0-100): "),
            releaseYear = readNextInt("Release year: "),
            genre = readNextLine("Genre: ").trim(),
        )

    private fun addGame() {
        val details = readGameDetails()

        try {
            val game = gameService.addGame(
                details.title, details.version, details.rating, details.releaseYear, details.genre
            )
            println("Game added:\n$game")
        } catch (e: IllegalArgumentException) {
            println("Game not added: ${e.message}")
        }
    }

    private fun updateGame() {
        if (gameService.getGames().isEmpty()) {
            println("No games stored")
            return
        }

        listGames()
        val id = readNextInt("ID of the game to update: ")
        val existing = gameService.findGameById(id)
        if (existing == null) {
            println("No game with ID $id")
            return
        }

        println("Updating:\n$existing")
        val details = readGameDetails()

        try {
            val updated = gameService.updateGame(
                id, details.title, details.version, details.rating, details.releaseYear, details.genre
            )
            println(if (updated) "Game updated" else "Game not found")
        } catch (e: IllegalArgumentException) {
            println("Game not updated: ${e.message}")
        }
    }

    private fun deleteGame() {
        if (gameService.getGames().isEmpty()) {
            println("No games stored")
            return
        }

        listGames()
        val id = readNextInt("ID of the game to delete: ")
        val game = gameService.findGameById(id)
        if (game == null) {
            println("No game with ID $id")
            return
        }

        if (readNextBoolean("Delete '${game.displayTitle}'? (y/n): ")) {
            println(if (gameService.deleteGame(id)) "Game deleted" else "Game not found")
        } else {
            println("Delete cancelled")
        }
    }
}