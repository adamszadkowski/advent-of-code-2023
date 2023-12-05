package pl.allegro.tdd

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Day1Test {

    @Nested
    inner class `part 1` {

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

    @Nested
    inner class `part 2` {

        @ParameterizedTest
        @CsvSource(
            "12,     12",
            "1,      11",
            "a1,     11",
            "aa1,    11",
            "one2,   12",
            "aone2,  12",
            "two1,   21",
            "1two,   12",
            "onetwo, 12",
            "twone,  21",
            "three,  33",
            "four,   44",
            "five,   55",
            "six,    66",
            "seven,  77",
            "eight,  88",
            "nine,   99",
        )
        fun `extract simple`(input: String, output: Int) {
            expectThat(calculate(input)).isEqualTo(output)
        }

        @Test
        fun `test sample data`() {
            val input = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
        """.trimIndent()

            expectThat(calculate(input))
                .isEqualTo(29 + 83 + 13 + 24 + 42 + 14 + 76)
                .isEqualTo(281)
        }

        @Test
        fun `calculate input`() {
            val input = Day1Test::class.java.classLoader.getResource("day-1/input.txt").readText()

            val output = calculate(input)

            expectThat(output).isEqualTo(55413)
        }

        private fun calculate(input: String): Int {
            val digits = mapOf(
                "one" to "1",
                "two" to "2",
                "three" to "3",
                "four" to "4",
                "five" to "5",
                "six" to "6",
                "seven" to "7",
                "eight" to "8",
                "nine" to "9",
            )

            fun extractDigitFrom(line: String, i: Int) =
                if (line[i].isDigit()) {
                    line.substring(i, i + 1)
                } else {
                    digits.entries.find { (spelled, _) -> line.substring(i).startsWith(spelled) }!!.value
                }

            fun findFirst(line: String): String {
                var i = 0
                while (!(line[i].isDigit() || digits.keys.any { line.substring(i).startsWith(it) })) {
                    i++
                }

                return extractDigitFrom(line, i)
            }


            fun findLast(line: String): String {
                var i = line.length - 1
                while (!(line[i].isDigit() || digits.keys.any { line.substring(i).startsWith(it) })) {
                    i--
                }

                return extractDigitFrom(line, i)
            }

            return input.split("\n").sumOf { (findFirst(it) + findLast(it)).toInt() }
        }
    }
}
