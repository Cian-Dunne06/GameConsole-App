package service

import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GameServiceTest {

    private val service = GameService()

    private fun addGame(
        title: String = "Zelda",
        rating: Int = 97,
        releaseDate: LocalDate = LocalDate.of(2017, 3, 3),
        genre: String = "Adventure"
    ) = service.addGame(title, rating, releaseDate, genre)

    @Test
    fun `a new service has no games`() {
        assertTrue(service.getGames().isEmpty())
    }

    @Test
    fun `addGame stores the game and returns it`() {
        val game = addGame()
        assertEquals("Zelda", game.title)
        assertEquals(97, game.rating)
        assertEquals(listOf(game), service.getGames())
    }

    @Test
    fun `ids are assigned in sequence starting at 0`() {
        assertEquals(0, addGame().gameId)
        assertEquals(1, addGame(title = "Mario").gameId)
        assertEquals(2, addGame(title = "Halo").gameId)
    }

    @Test
    fun `adding an invalid game is rejected and nothing is stored`() {
        assertFailsWith<IllegalArgumentException> { addGame(title = "") }
        assertTrue(service.getGames().isEmpty())
    }

    @Test
    fun `a rejected add does not use up an id`() {
        assertFailsWith<IllegalArgumentException> { addGame(rating = 101) }
        assertEquals(0, addGame().gameId)
    }
}