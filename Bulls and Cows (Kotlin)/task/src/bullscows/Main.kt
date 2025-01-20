package bullscows

import kotlin.random.Random

fun main() {
    val bullsAndCows = BullsAndCows()
    bullsAndCows.play()
}

class BullsAndCows {

    private var turn = 1
    private var secretCode = mutableListOf<Char>()
    private var isGameOver = false
    private var lengthOfSecretCode = 0
    private var numOfPossibleSymbols = 0
    private var isInputValid = false

    private val numberRange = '0'..'9'
    private val alphabetRange = 'a'..'z'
    private val characterList = numberRange + alphabetRange

    private val characterFlags = mutableSetOf<Char>()

    fun play() {
        promptInput()
        generateCode()

        println(START_GAME_MESSAGE)
        do {
            println(TURN_TEXT.format(turn))
            val playerGuess = readln()
            grade(playerGuess)
            turn++
        } while(!isGameOver)

        println(WIN_MESSAGE)
    }

    private fun promptInput() {
        do {
            println(INPUT_LENGTH_PROMPT)
            lengthOfSecretCode = readln().toInt()

            println(INPUT_NUM_SYMBOLS)
            numOfPossibleSymbols = readln().toInt()

            isInputValid = lengthOfSecretCode in 1..MAX_LENGTH &&
                    numOfPossibleSymbols in 2..MAX_LENGTH &&
                    lengthOfSecretCode <= numOfPossibleSymbols

            if (!isInputValid) println(ERROR_MESSAGE)
        } while(!isInputValid)
    }

    private fun grade(guess: String) {
        val input = guess.toCharArray()

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

    private fun generateCode() {
        var isConditionMet: Boolean
        do {
            val index = Random.nextInt(0, numOfPossibleSymbols)
            val selectedChar = characterList[index]

            if (!characterFlags.contains(selectedChar)) characterFlags.add(selectedChar)

            isConditionMet = characterFlags.size == numOfPossibleSymbols
        } while(!isConditionMet)

        secretCode.addAll(characterFlags.take(lengthOfSecretCode))
        val asterisks = "*".repeat(lengthOfSecretCode)
        val range = getTextOfRange()

        println(SECRET_PREPARED_MESSAGE.format(asterisks, range))
    }

    private fun getTextOfRange(): String {
        val remainingTextOfRange = when {
            numOfPossibleSymbols == 2 -> "1"
            numOfPossibleSymbols <= DIGIT_LENGTH -> "${characterList[numOfPossibleSymbols - 1]}"

            else -> {
                if (numOfPossibleSymbols - DIGIT_LENGTH == 1) {
                    "9, a-a"
                } else {
                    "9, a-${characterList[numOfPossibleSymbols - 1]}"
                }
            }
        }

        return "(0-%s).".format(remainingTextOfRange)
    }

    companion object {
        const val ERROR_MESSAGE = "Error: can't generate a secret number with a length of 11 because there aren't enough unique digits."
        const val MAX_LENGTH = 36
        const val DIGIT_LENGTH = 10

        const val INPUT_LENGTH_PROMPT = "Input the length of the secret code:"
        const val INPUT_NUM_SYMBOLS = "Input the number of possible symbols in the code:"
        const val START_GAME_MESSAGE = "Okay, let's start a game!"

        const val SINGULAR_BULL = "bull"
        const val SINGULAR_COW = "cow"
        const val PLURAL_BULL = "bulls"
        const val PLURAL_COW = "cows"

        const val WIN_MESSAGE = "Congratulations! You guessed the secret code."
        const val TURN_TEXT = "Turn %d:"
        const val SECRET_PREPARED_MESSAGE = "The secret is prepared: %1\$s %2\$s"
    }
}