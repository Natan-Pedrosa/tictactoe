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
    board = makeMove(board, "X")
    show(board)
}

fun initGame(): MutableList<MutableList<String>> {
    return mutableListOf(
        mutableListOf("_", "_", "_"),
        mutableListOf("_", "_", "_"),
        mutableListOf("_", "_", "_"))
}
fun show(board: MutableList<MutableList<String>>) {

    println("---------")
    println("| ${board[0][0]} ${board[0][1]} ${board[0][2]} |")
    println("| ${board[1][0]} ${board[1][1]} ${board[1][2]} |")
    println("| ${board[2][0]} ${board[2][1]} ${board[2][2]} |")
    println("---------")
}
fun start(): String {
    print("Digit:")
    val msn = readln()
    return msn
}
fun makeMove(board: MutableList<MutableList<String>>, newValue: String):MutableList<MutableList<String>> {
    val newBoard = board

    while(true) {
        val input = readln().split(" ")

        val (valueFirst, valueSecond ) = if (input.size == 2) input else "e e".split(" ")

        if (valueFirst[0].isDigit() || valueSecond[0].isDigit()) {
            if (valueFirst in "123" && valueSecond in "123") {
                if (newBoard[valueFirst.toInt() - 1][valueSecond.toInt() - 1] == "_") {
                    newBoard[valueFirst.toInt() - 1][valueSecond.toInt() - 1] = newValue

                    return newBoard
                } else println("This cell is occupied! Choose another one!")
            } else println("Coordinates should be from 1 to 3!")
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