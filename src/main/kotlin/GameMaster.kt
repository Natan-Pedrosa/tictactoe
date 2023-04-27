/*
 */
const val MIN_VALUE = 0
const val MAX_VALUE = 3
const val WIN_X = "X wins"
const val WIN_O = "O wins"
const val GAME_NO_FINISHED = "Game not finished"
const val IMPOSSIBLE = "Impossible"
const val DRAW = "Draw"
const val VALUE_X = "X"
const val VALUE_O = "O"

fun main() {
    game()
}

fun game() {
    var board = initGame()
    show(board)
    var isTurnForX = true
    var hasNextMove = true
    var result = ""

    while(hasNextMove) {
        when (isTurnForX) {
            true -> {
                board = makeMove(board, VALUE_X)
                isTurnForX = false
            }
            false -> {
                board = makeMove(board, VALUE_O)
                isTurnForX = true
            }
        }
        result = showResult(board)
        hasNextMove = result != WIN_X && result != WIN_O
        show(board)
    }
    println(result)
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

fun showResult(board: MutableList<MutableList<String>>): String {
    val wins = mutableListOf(
        /*
        |0 1 2|
        |3 4 5|
        |6 7 8|
         */
        mutableListOf(0, 0, 0, 1, 0, 2),
        mutableListOf(1, 0, 1, 1, 1, 2),
        mutableListOf(2, 0, 2, 1, 2, 2),
        mutableListOf(0, 0, 1, 1, 2, 2),
        mutableListOf(0, 2, 1, 1, 2, 0),
        mutableListOf(0, 0, 1, 0, 2, 0),
        mutableListOf(0, 1, 1, 1, 2, 1),
        mutableListOf(0, 2, 1, 2, 2, 2),
        )

    val msn = mutableListOf(GAME_NO_FINISHED, WIN_X, WIN_O, DRAW, IMPOSSIBLE)
    var indexMsn = 0
    var hasOneWin = 0

    for (index in 0 .. wins.lastIndex) {
        // val isValueToWin = board[wins[].toString() + board[wins[index][1]] + board[wins[index][2]]
        val isValueToWin = buildString {
        append(board[wins[index][0]][wins[index][1]])
        append(board[wins[index][2]][wins[index][3]])
        append(board[wins[index][4]][wins[index][5]])
    }

        when (isValueToWin) {
            VALUE_X.repeat(3) -> {indexMsn = 1; ++hasOneWin}
            VALUE_O.repeat(3) -> {indexMsn = 2; ++hasOneWin}
        }
    }

    var countX = 0
    var countO = 0
    var countUnderLine = 0

    if (indexMsn == 0) {
        for (line in 0..2) {
            for (column in 0..2) {
                when (board[line][column]) {
                    VALUE_X -> countX++
                    VALUE_O -> countO++
                    else -> countUnderLine++
                }
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