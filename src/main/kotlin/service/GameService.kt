package service

import model.Game
import java.time.LocalDate

class GameService {

    private val games = ArrayList<Game>()
    private var nextId = 0

    fun addGame(title: String, rating: Int, releaseDate: LocalDate, genre: String): Game {
        val game = Game(nextId, title, rating, releaseDate, genre)
        nextId++
        games.add(game)
        return game
    }

    fun getGames(): List<Game> = games

    fun findGameById(id: Int): Game? = games.find { it.gameId == id }

    fun updateGame(id: Int, title: String, rating: Int, releaseDate: LocalDate, genre: String): Boolean {
        val index = games.indexOfFirst { it.gameId == id }
        if (index == -1) return false
        games[index] = Game(id, title, rating, releaseDate, genre)
        return true
    }

    fun deleteGame(id: Int): Boolean = games.removeIf { it.gameId == id }
}