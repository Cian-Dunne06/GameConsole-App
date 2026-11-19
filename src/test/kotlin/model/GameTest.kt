package model


import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import java.time.Year

class GameTest {

    private fun validGame(
        gameId: Int = 1,
        title: String = "Zelda",
        rating: Int = 97,
        releaseYear: Int = 2017,
        genre: String = "Adventure"
    ) = Game(gameId, title, rating, releaseYear, genre)

    @Test
    fun `game is created with the supplied values`() {
        val game = validGame()
        assertEquals(1, game.gameId)
        assertEquals("Zelda", game.title)
        assertEquals(97, game.rating)
        assertEquals(2017, game.releaseYear)
        assertEquals("Adventure", game.genre)
    }

    @Test
    fun `ratings of 0 and 100 are accepted`() {
        assertEquals(0, validGame(rating = 0).rating)
        assertEquals(100, validGame(rating = 100).rating)
    }

    @Test
    fun `rating below 0 is rejected`() {
        assertFailsWith<IllegalArgumentException> { validGame(rating = -1) }
    }

    @Test
    fun `rating above 100 is rejected`() {
        assertFailsWith<IllegalArgumentException> { validGame(rating = 101) }
    }

    @Test
    fun `blank title is rejected`() {
        assertFailsWith<IllegalArgumentException> { validGame(title = "  ") }
    }

    @Test
    fun `blank genre is rejected`() {
        assertFailsWith<IllegalArgumentException> { validGame(genre = "") }
    }

    @Test
    fun `negative game id is rejected`() {
        assertFailsWith<IllegalArgumentException> { validGame(gameId = -1) }
    }

    @Test
    fun `copy changes only the specified field`() {
        val updated = validGame().copy(rating = 5)
        assertEquals(5, updated.rating)
        assertEquals("Zelda", updated.title)
    }

    @Test
    fun `copy with an invalid rating is rejected`() {
        assertFailsWith<IllegalArgumentException> { validGame().copy(rating = 500) }
    }

    @Test
    fun `toString shows id, title, rating, genre and release date on separate lines`() {
        val expected = listOf(
            "(1) Zelda",
            "Rating: 97/100 | Genre: Adventure",
            "Release Year: 2017"
        )
        assertEquals(expected, validGame().toString().lines())
    }

    @Test
    fun `release years at the lower and upper limits are accepted`() {
        assertEquals(1958, validGame(releaseYear = 1958).releaseYear)
        assertEquals(Year.now().value + 1, validGame(releaseYear = Year.now().value + 1).releaseYear)
    }

    @Test
    fun `release year below the lower limit is rejected`() {
        assertFailsWith<IllegalArgumentException> { validGame(releaseYear = 1957) }
    }

    @Test
    fun `release year above the upper limit is rejected`() {
        assertFailsWith<IllegalArgumentException> { validGame(releaseYear = Year.now().value + 2) }
    }
}