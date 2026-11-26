package service

import model.Console
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ConsoleServiceTest {

    private val service = ConsoleService()

    private fun addConsole(
        name: String = "PlayStation 5",
        company: String = "Sony",
        releaseYear: Int = 2020,
        cost: Double = 499.99,
        isSupported: Boolean = true
    ) = service.addConsole(name, company, releaseYear, cost, isSupported)

    @Nested
    inner class GetConsoles {

        @Test
        fun `a new service has no consoles`() {
            assertTrue(service.getConsoles().isEmpty())
        }
    }

    @Nested
    inner class AddConsole {

        @Test
        fun `addConsole stores the console and returns it`() {
            val console = addConsole()
            assertEquals("PlayStation 5", console.name)
            assertEquals("Sony", console.company)
            assertEquals(listOf(console), service.getConsoles())
        }

        @Test
        fun `ids are assigned in sequence starting at 0`() {
            assertEquals(0, addConsole().consoleId)
            assertEquals(1, addConsole(name = "Xbox Series X").consoleId)
            assertEquals(2, addConsole(name = "Switch").consoleId)
        }

        @Test
        fun `adding an invalid console is rejected and nothing is stored`() {
            assertFailsWith<IllegalArgumentException> { addConsole(name = "") }
            assertTrue(service.getConsoles().isEmpty())
        }

        @Test
        fun `a rejected add does not use up an id`() {
            assertFailsWith<IllegalArgumentException> { addConsole(cost = -1.0) }
            assertEquals(0, addConsole().consoleId)
        }
    }

    @Nested
    inner class FindConsoleById {

        @Test
        fun `findConsoleById returns the console with that id`() {
            val ps5 = addConsole()
            val xbox = addConsole(name = "Xbox Series X")
            assertEquals(ps5, service.findConsoleById(ps5.consoleId))
            assertEquals(xbox, service.findConsoleById(xbox.consoleId))
        }

        @Test
        fun `findConsoleById returns null when no console has that id`() {
            addConsole()
            assertNull(service.findConsoleById(99))
        }

        @Test
        fun `findConsoleById returns null on an empty service`() {
            assertNull(service.findConsoleById(0))
        }
    }

    @Nested
    inner class UpdateConsole {

        @Test
        fun `updateConsole replaces the values and returns true`() {
            val original = addConsole()
            val result = service.updateConsole(original.consoleId, "Switch", "Nintendo", 2017, 299.99, false)
            assertTrue(result)
            assertEquals(
                Console(original.consoleId, "Switch", "Nintendo", 2017, 299.99, false),
                service.findConsoleById(original.consoleId)
            )
        }

        @Test
        fun `updateConsole changes only the console with that id`() {
            val ps5 = addConsole()
            val xbox = addConsole(name = "Xbox Series X")
            service.updateConsole(ps5.consoleId, "Switch", "Nintendo", 2017, 299.99, false)
            assertEquals(xbox, service.findConsoleById(xbox.consoleId))
            assertEquals(2, service.getConsoles().size)
        }

        @Test
        fun `updateConsole returns false and changes nothing when the id does not exist`() {
            val ps5 = addConsole()
            assertFalse(service.updateConsole(99, "Switch", "Nintendo", 2017, 299.99, false))
            assertEquals(listOf(ps5), service.getConsoles())
        }

        @Test
        fun `updateConsole with invalid data is rejected and the stored console is unchanged`() {
            val ps5 = addConsole()
            assertFailsWith<IllegalArgumentException> {
                service.updateConsole(ps5.consoleId, "", "Nintendo", 2017, 299.99, false)
            }
            assertEquals(ps5, service.findConsoleById(ps5.consoleId))
        }
    }

    @Nested
    inner class DeleteConsole {

        @Test
        fun `deleteConsole removes the console and returns true`() {
            val ps5 = addConsole()
            assertTrue(service.deleteConsole(ps5.consoleId))
            assertNull(service.findConsoleById(ps5.consoleId))
            assertTrue(service.getConsoles().isEmpty())
        }

        @Test
        fun `deleteConsole removes only the console with that id`() {
            val ps5 = addConsole()
            val xbox = addConsole(name = "Xbox Series X")
            service.deleteConsole(ps5.consoleId)
            assertEquals(listOf(xbox), service.getConsoles())
        }

        @Test
        fun `deleteConsole returns false and changes nothing when the id does not exist`() {
            val ps5 = addConsole()
            assertFalse(service.deleteConsole(99))
            assertEquals(listOf(ps5), service.getConsoles())
        }

        @Test
        fun `deleteConsole returns false on an empty service`() {
            assertFalse(service.deleteConsole(0))
        }

        @Test
        fun `ids are not reused after a delete`() {
            val ps5 = addConsole()
            addConsole(name = "Xbox Series X")
            service.deleteConsole(ps5.consoleId)
            assertEquals(2, addConsole(name = "Switch").consoleId)
            assertEquals(3, addConsole(name = "Steam Deck").consoleId)
        }
    }
}