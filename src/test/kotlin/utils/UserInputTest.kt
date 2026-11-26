package utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class UserInputTest {

    @Test
    fun `an empty string means no value`() {
        assertNull(parseOptionalInt(""))
    }

    @Test
    fun `a string of only spaces means no value`() {
        assertNull(parseOptionalInt("   "))
    }

    @Test
    fun `a number is parsed`() {
        assertEquals(2, parseOptionalInt("2"))
    }

    @Test
    fun `spaces around a number are ignored`() {
        assertEquals(3, parseOptionalInt("  3 "))
    }

    @Test
    fun `zero is parsed and left for the model to reject`() {
        assertEquals(0, parseOptionalInt("0"))
    }

    @Test
    fun `text that is not a number is rejected`() {
        assertFailsWith<NumberFormatException> { parseOptionalInt("abc") }
    }

    @Test
    fun `a decimal is rejected`() {
        assertFailsWith<NumberFormatException> { parseOptionalInt("2.5") }
    }

    @Test
    fun `yes answers are parsed as true`() {
        assertEquals(true, parseYesNo("y"))
        assertEquals(true, parseYesNo("yes"))
        assertEquals(true, parseYesNo("true"))
    }

    @Test
    fun `no answers are parsed as false`() {
        assertEquals(false, parseYesNo("n"))
        assertEquals(false, parseYesNo("no"))
        assertEquals(false, parseYesNo("false"))
    }

    @Test
    fun `case and surrounding spaces are ignored`() {
        assertEquals(true, parseYesNo("  YES "))
        assertEquals(false, parseYesNo(" No"))
    }

    @Test
    fun `an unrecognised answer is null`() {
        assertNull(parseYesNo("yse"))
        assertNull(parseYesNo("maybe"))
    }

    @Test
    fun `an empty answer is null`() {
        assertNull(parseYesNo(""))
    }
}