/*
 */
const val MIN_VALUE = 0
const val MAX_VALUE = 3
fun main() {
    game()
}

fun game() {
    var board = initGame()
    show(board)
    board = enterNewValue(enter)
    // show(board)
}

fun initGame(): MutableList<MutableList<String>> {
    return mutableListOf(
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " "))
}

fun start(): String {
    print("Digit:")
    val msn = readln()
    return msn
}

fun show(board: MutableList<MutableList<String>>) {
    /* var line = ""
    for ((index, value ) in newBoard.withIndex()) {
        when (index) {
            0, 3, 6 -> line = "| $value"
            1, 4, 7 -> line += " $value"
            else -> println("$line $value |")
        }
    }
    */
    println("---------")
    println("| ${board[0][0]} ${board[0][1]} ${board[0][2]} |")
    println("| ${board[1][0]} ${board[1][1]} ${board[1][2]} |")
    println("| ${board[2][0]} ${board[2][1]} ${board[2][2]} |")
    println("---------")
    //println(showResult(msn))
}

fun enterNewValue(values: String):String {
    val board = mutableListOf(
        mutableListOf(values[0], values[1], values[2]),
        mutableListOf(values[3], values[4], values[5]),
        mutableListOf(values[6], values[7], values[8])
    )

    while(true) {
        print("Digit:")
        val input = readln().split(" ")

        val (valueFirst, valueSecond ) = if (input.size == 2) input else "e e".split("")

        if (valueFirst in "123" && valueSecond in "123") {
            if (board[valueFirst.toInt() - 1][valueSecond.toInt() - 1] == '_') {
                board[valueFirst.toInt() - 1][valueSecond.toInt() - 1] = 'X'
                var result = ""
                for (index in 0 until 3) {
                    board[index].forEach { value ->
                        result += value
                    }
                }

                return result
            } else {
                println("This cell is occupied! Choose another one!")
            }
        } else if (valueFirst in "0456789" || valueSecond in "0456789") {
            println("Coordinates should be from 1 to 3!")
        } else println("You should enter numbers!")
    }
}

fun showResult(values: String): String {
    val wins = mutableListOf(
        mutableListOf(0, 1, 2),
        mutableListOf(3, 4, 5),
        mutableListOf(6, 7, 8),
        mutableListOf(0, 4, 8),
        mutableListOf(2, 4, 6),
        mutableListOf(0, 3, 6),
        mutableListOf(1, 4, 7),
        mutableListOf(2, 5, 8),
        )
    val msn = mutableListOf("Game not finished", "X wins", "O wins", "Draw", "Impossible")
    var indexMsn = 0
    var hasOneWin = 0
    for (index in 0 .. wins.lastIndex) {
        val isValueToWin = values[wins[index][0]].toString() + values[wins[index][1]] + values[wins[index][2]]

        when (isValueToWin) {
            "XXX" -> {indexMsn = 1; ++hasOneWin}
            "OOO" -> {indexMsn = 2; ++hasOneWin}
            else -> {}
        }
    }

    var countX = 0
    var countO = 0
    var countUnderLine = 0

    if (indexMsn == 0) {
       for (value in values) {
            when (value) {
                'X' -> countX++
                'O' -> countO++
                else -> countUnderLine++
            }
       }
        val sumXO = countX + countO

        indexMsn = if (sumXO < 9) {
            when (countX) {
                countO + 1 -> 0
                countO - 1 -> 0
                countO -> 0
                else -> 4
            }
        } else {
            3
        }
    } else {
      indexMsn = (if (hasOneWin > 1) 4 else indexMsn)
    }

    return msn[indexMsn]
}