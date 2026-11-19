package service

import model.Game

class GameService {

    private val games = ArrayList<Game>()
    private var nextId = 0

    fun addGame(title: String, version: Int?, rating: Int, releaseYear: Int, genre: String): Game {
        val game = Game(nextId, title, version, rating, releaseYear, genre)
        nextId++
        games.add(game)
        return game
    }

    fun getGames(): List<Game> = games

    fun findGameById(id: Int): Game? = games.find { it.gameId == id }

    fun updateGame(id: Int, title: String, version: Int?, rating: Int, releaseYear: Int, genre: String): Boolean {
        val index = games.indexOfFirst { it.gameId == id }
        if (index == -1) return false
        games[index] = Game(id, title, version, rating, releaseYear, genre)
        return true
    }

    fun deleteGame(id: Int): Boolean = games.removeIf { it.gameId == id }
}