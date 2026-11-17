package model

import java.time.LocalDate

data class Game(
    val gameId: Int,
    val title: String,
    val rating: Int,
    val releaseDate: LocalDate,
    val genre: String
) {
    init {
        require(gameId >= 0) { "gameId must not be negative" }
        require(title.isNotEmpty()) { "title must not be blank" }
        require(rating in 0..100) { "rating must be between 0 and 100" }
        require(genre.isNotEmpty()) { "genre must not be blank" }
    }
}