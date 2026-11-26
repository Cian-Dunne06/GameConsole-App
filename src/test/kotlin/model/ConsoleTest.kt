package model

import java.time.Year
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ConsoleTest {

    private fun validConsole(
        consoleId: Int = 1,
        name: String = "PlayStation 5",
        company: String = "Sony",
        releaseYear: Int = 2020,
        cost: Double = 499.99,
        isSupported: Boolean = true
    ) = Console(consoleId, name, company, releaseYear, cost, isSupported)

    @Test
    fun `console is created with the supplied values`() {
        val console = validConsole()
        assertEquals(1, console.consoleId)
        assertEquals("PlayStation 5", console.name)
        assertEquals("Sony", console.company)
        assertEquals(2020, console.releaseYear)
        assertEquals(499.99, console.cost)
        assertEquals(true, console.isSupported)
    }

    @Test
    fun `blank name is rejected`() {
        assertFailsWith<IllegalArgumentException> { validConsole(name = "  ") }
    }

    @Test
    fun `blank company is rejected`() {
        assertFailsWith<IllegalArgumentException> { validConsole(company = "") }
    }

    @Test
    fun `negative console id is rejected`() {
        assertFailsWith<IllegalArgumentException> { validConsole(consoleId = -1) }
    }

    @Test
    fun `release years at the lower and upper limits are accepted`() {
        assertEquals(1972, validConsole(releaseYear = 1972).releaseYear)
        assertEquals(Year.now().value + 1, validConsole(releaseYear = Year.now().value + 1).releaseYear)
    }

    @Test
    fun `release year below the lower limit is rejected`() {
        assertFailsWith<IllegalArgumentException> { validConsole(releaseYear = 1971) }
    }

    @Test
    fun `release year above the upper limit is rejected`() {
        assertFailsWith<IllegalArgumentException> { validConsole(releaseYear = Year.now().value + 2) }
    }

    @Test
    fun `a cost of zero is accepted`() {
        assertEquals(0.0, validConsole(cost = 0.0).cost)
    }

    @Test
    fun `negative cost is rejected`() {
        assertFailsWith<IllegalArgumentException> { validConsole(cost = -0.01) }
    }

    @Test
    fun `toString shows id, name, company, year, cost and support status on separate lines`() {
        val expected = listOf(
            "(1) PlayStation 5",
            "Company: Sony | Released: 2020",
            "Cost: 499.99 | Supported: Yes"
        )
        assertEquals(expected, validConsole().toString().lines())
    }

    @Test
    fun `toString shows No when the console is not supported`() {
        val lastLine = validConsole(isSupported = false).toString().lines().last()
        assertEquals("Cost: 499.99 | Supported: No", lastLine)
    }

    @Test
    fun `infinite cost is rejected`() {
        assertFailsWith<IllegalArgumentException> { validConsole(cost = Double.POSITIVE_INFINITY) }
    }

    @Test
    fun `NaN cost is rejected`() {
        assertFailsWith<IllegalArgumentException> { validConsole(cost = Double.NaN) }
    }
}