/*
 */
fun main() {
    game()
}

fun game() {
    val enter = start()
    show(enter)
}

fun start(): String {
    print("Digit:")
    val msn = readln()
    return msn
}

fun show(msn: String) {
    val new_msn = msn
    println("---------")
    var line = ""
    for ((index, value ) in new_msn.withIndex()) {
        when (index) {
            0, 3, 6 -> line = "| $value"
            1, 4, 7 -> line += " $value"
            else -> println("$line $value |")
        }
    }
    println("---------")
    println(showResult(msn))
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