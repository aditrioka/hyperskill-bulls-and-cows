package bullscows

const val WELCOME_MESSAGE = "The secret code is prepared: ****."

fun main() {
    println(WELCOME_MESSAGE)

    println("""
        Turn 1. Answer:
        1234
        Grade: None.

        Turn 2. Answer:
        9876
        Grade: 4 bulls.
        Congrats! The secret code is 9876.
    """.trimIndent())
}