package model

import java.time.Year

data class Game(
    val gameId: Int,
    val title: String,
    val version: Int?,
    val rating: Int,
    val releaseYear: Int,
    val genre: String
)

{
    init {
        require(gameId >= 0) { "gameId must not be negative" }
        require(title.isNotBlank()) { "title must not be blank" }
        require(version == null || version >= 1) { "version must be at least 1 when given" }
        require(rating in 0..100) { "rating must be between 0 and 100" }
        require(genre.isNotBlank()) { "genre must not be blank" }
        require(releaseYear in 1958 .. Year.now().value + 1) { "releaseYear must be between 1958 and next year" }

    }

    val displayTitle: String
        get() = if (version == null) title else "$title $version"

    override fun toString(): String =
        """
        |($gameId) $displayTitle
        |Rating: $rating/100 | Genre: $genre
        |Release Year: $releaseYear
        """.trimMargin()
}