package pl.allegro.tdd

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Day1Test {

    @ParameterizedTest
    @CsvSource(
        "12,    12",
        "1a2,   12",
        "a1a2f, 12",
        "1a23,  13",
        "a2f,   22",
    )
    fun `calculate single line`(input: String, output: Int) {
        expectThat(calculate(input)).isEqualTo(output)
    }

    @Test
    fun `calculate two lines`() {
        val input = """
            12
            23
        """.trimIndent()

        expectThat(calculate(input)).isEqualTo(12 + 23)
    }

    @Test
    fun `test sample data`() {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()

        expectThat(calculate(input))
            .isEqualTo(12 + 38 + 15 + 77)
            .isEqualTo(142)
    }

    @Test
    fun `calculate input`() {
        val input = Day1Test::class.java.classLoader.getResource("day-1/input.txt").readText()

        val output = calculate(input)

        expectThat(output).isEqualTo(55712)
    }

    private fun calculate(input: String): Int =
        input
            .split("\n")
            .sumOf { line ->
                val digits = line.filter { it.isDigit() }.map { it.digitToInt() }
                digits.first() * 10 + digits.last()
            }
}
