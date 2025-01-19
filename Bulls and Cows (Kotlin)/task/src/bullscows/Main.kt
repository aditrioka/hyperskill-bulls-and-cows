package bullscows

const val WELCOME_MESSAGE = "The secret code is prepared: ****."
const val SECRET_CODE = 9305

fun main() {
    grade()
}

fun printMessage() {
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

fun grade() {
    val input = readln().toCharArray().map { it.code }
    val secretCode = SECRET_CODE.toString().toCharArray().map { it.code }

    var numOfBulls = 0
    var numOfCows = 0

    code@ for (code in secretCode) {
        for (number in input) {
            if (code == number) {
                numOfCows++
                continue@code
            }
        }
    }

    for (index in secretCode.indices) {
        if (secretCode[index] == input[index]) numOfBulls++
    }

    numOfCows -= numOfBulls

    println("Grade: $numOfBulls bull(s) and $numOfCows cow(s). The secret code is $SECRET_CODE.")
}