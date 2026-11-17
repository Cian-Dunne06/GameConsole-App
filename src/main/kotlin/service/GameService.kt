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
}