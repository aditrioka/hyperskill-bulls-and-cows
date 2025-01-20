package bullscows

fun main() {
    val bullsAndCows = BullsAndCows()
    bullsAndCows.play()
}

class BullsAndCows {

    private var turn = 1
    private var secretCode = mutableListOf<Int>()
    private var isGameOver = false

    fun play() {
        println(ENTER_LENGTH_PROMPT)
        val length = readln().toInt()
        generateNumber(length)

        println(START_GAME_MESSAGE)
        do {
            println(TURN_TEXT.format(turn))
            val playerGuess = readln()
            grade(playerGuess)
            turn++
        } while(!isGameOver)

        println(WIN_MESSAGE)
    }

    private fun grade(guess: String) {
        val input = guess.toCharArray().map { it.toString().toInt() }

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

        val cowType = if (numOfCows > 1) PLURAL_COW else SINGULAR_COW
        val bullType = if (numOfBulls > 1) PLURAL_BULL else SINGULAR_BULL

        println("Grade: $numOfBulls $bullType and $numOfCows $cowType.")
        if (numOfBulls == secretCode.size) isGameOver = true
    }

    private fun generateNumber(length: Int) {
        if (length > MAX_LENGTH) {
            println(ERROR_MESSAGE)
        } else {
            var pseudoRandomNumber: MutableList<Int>

            do {
                pseudoRandomNumber = emptyList<Int>().toMutableList()
                val numberCount = BooleanArray(10)

                val systemNanoTime = System.nanoTime().toString().reversed()
                val charArray = systemNanoTime.toCharArray().map { it.digitToInt() }

                charArray.forEach { digit ->
                    if (!numberCount[digit]) {
                        numberCount[digit] = true
                        pseudoRandomNumber += digit
                    }
                }

                val isNumberValid = pseudoRandomNumber.size >= length
            } while(!isNumberValid)

            secretCode.addAll(pseudoRandomNumber.take(length))
        }
    }

    companion object {
        const val WELCOME_MESSAGE = "The secret code is prepared: ****."
        const val ERROR_MESSAGE = "Error: can't generate a secret number with a length of 11 because there aren't enough unique digits."
        const val MAX_LENGTH = 10
        const val ENTER_LENGTH_PROMPT = "Please, enter the secret code's length:"
        const val START_GAME_MESSAGE = "Okay, let's start a game!"
        const val SINGULAR_BULL = "bull"
        const val SINGULAR_COW = "cow"
        const val PLURAL_BULL = "bulls"
        const val PLURAL_COW = "cows"
        const val WIN_MESSAGE = "Congratulations! You guessed the secret code."
        const val GRADE_TEXT = "Grade: "
        const val AND_TEXT = "and"
        const val TURN_TEXT = "Turn %d:"
    }
}