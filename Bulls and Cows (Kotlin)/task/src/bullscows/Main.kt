package bullscows

const val WELCOME_MESSAGE = "The secret code is prepared: ****."
const val SECRET_CODE = 9305
const val ERROR_MESSAGE = "Error: can't generate a secret number with a length of 11 because there aren't enough unique digits."
const val MAX_LENGTH = 10

fun main() {
    generatePseudoRandomNumber()
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
    val input = readln().toCharArray().map { it.toString().toInt() }
    val secretCode = SECRET_CODE.toString().toCharArray().map { it.toString().toInt() }

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

fun generatePseudoRandomNumber() {
    val length = readln().toInt()

    if (length > MAX_LENGTH) {
        println(ERROR_MESSAGE)
    } else {
        var pseudoRandomNumber: String

        do {
            pseudoRandomNumber = ""
            val numberCount = BooleanArray(10)

            val systemNanoTime = System.nanoTime().toString().reversed()
            val charArray = systemNanoTime.toCharArray().map { it.digitToInt() }

            charArray.forEach { digit ->
                if (!numberCount[digit]) {
                    numberCount[digit] = true
                    pseudoRandomNumber += digit
                }
            }

            val isNumberValid = pseudoRandomNumber.length >= length
        } while(!isNumberValid)

        println(pseudoRandomNumber.substring(0,  length))
    }
}