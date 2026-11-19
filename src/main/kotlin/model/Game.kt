package model

import java.time.Year

data class Game(
    val gameId: Int,
    val title: String,
    val rating: Int,
    val releaseYear: Int,
    val genre: String
) {
    init {
        require(gameId >= 0) { "gameId must not be negative" }
        require(title.isNotBlank()) { "title must not be blank" }
        require(rating in 0..100) { "rating must be between 0 and 100" }
        require(genre.isNotBlank()) { "genre must not be blank" }
        require(releaseYear in 1958 .. Year.now().value + 1) { "releaseYear must be between 1958 and next year" }
    }

    override fun toString(): String =
        """
        |($gameId) $title
        |Rating: $rating/100 | Genre: $genre
        |Release Year: $releaseYear
        """.trimMargin()
}