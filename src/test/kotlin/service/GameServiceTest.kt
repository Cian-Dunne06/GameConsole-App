package service

import model.Game
import org.junit.jupiter.api.Nested
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertNull

class GameServiceTest {

    private val service = GameService()

    private fun addGame(
        title: String = "Zelda",
        rating: Int = 97,
        releaseDate: LocalDate = LocalDate.of(2017, 3, 3),
        genre: String = "Adventure"
    ) = service.addGame(title, rating, releaseDate, genre)

    @Nested
    inner class GetGame {

        @Test
        fun `a new service has no games`() {
            assertTrue(service.getGames().isEmpty())
        }
    }

    @Nested
    inner class AddGame {

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

    @Nested
    inner class FindGameById {
        @Test
        fun `findGameById returns the game with that id`() {
            val zelda = addGame()
            val mario = addGame(title = "Mario")
            assertEquals(zelda, service.findGameById(zelda.gameId))
            assertEquals(mario, service.findGameById(mario.gameId))
        }

        @Test
        fun `findGameById returns null when no game has that id`() {
            addGame()
            assertNull(service.findGameById(99))
        }

        @Test
        fun `findGameById returns null on an empty service`() {
            assertNull(service.findGameById(0))
        }
    }

    @Nested
    inner class UpdateGame {

        private val newDate = LocalDate.of(2023, 5, 12)

        @Test
        fun `updateGame replaces the values and returns true`() {
            val original = addGame()
            val result = service.updateGame(original.gameId, "Mario", 80, newDate, "Platformer")
            assertTrue(result)
            assertEquals(
                Game(original.gameId, "Mario", 80, newDate, "Platformer"),
                service.findGameById(original.gameId)
            )
        }

        @Test
        fun `updateGame changes only the game with that id`() {
            val zelda = addGame()
            val mario = addGame(title = "Mario")
            service.updateGame(zelda.gameId, "Halo", 80, newDate, "Shooter")
            assertEquals(mario, service.findGameById(mario.gameId))
            assertEquals(2, service.getGames().size)
        }

        @Test
        fun `updateGame returns false and changes nothing when the id does not exist`() {
            val zelda = addGame()
            assertFalse(service.updateGame(99, "Mario", 80, newDate, "Platformer"))
            assertEquals(listOf(zelda), service.getGames())
        }

        @Test
        fun `updateGame with invalid data is rejected and the stored game is unchanged`() {
            val zelda = addGame()
            assertFailsWith<IllegalArgumentException> {
                service.updateGame(zelda.gameId, "", 80, newDate, "Platformer")
            }
            assertEquals(zelda, service.findGameById(zelda.gameId))
        }
    }
}