package model

import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GameTest {

    private fun validGame(
        gameId: Int = 1,
        title: String = "Zelda",
        rating: Int = 97,
        releaseDate: LocalDate = LocalDate.of(2017, 3, 3),
        genre: String = "Adventure"
    ) = Game(gameId, title, rating, releaseDate, genre)

    @Test
    fun `game is created with the supplied values`() {
        val game = validGame()
        assertEquals(1, game.gameId)
        assertEquals("Zelda", game.title)
        assertEquals(97, game.rating)
        assertEquals(LocalDate.of(2017, 3, 3), game.releaseDate)
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
}