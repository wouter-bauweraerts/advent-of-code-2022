fun rockPaperScissors(input: String, part1: Boolean): Int{
    return input.split("\n")
        .map {
            when(part1) {
                true -> parseGame(it)
                false -> parseGameTwo(it)
            } }
        .sumOf { it.score() }
}

private fun parseGame(gameInput: String): Game {
    val choices = gameInput.split(" ")
        .map { PlayerInput.valueOf(it) }
        .map {  it.choice }

    return Game(choices[0], choices[1])
}

private fun parseGameTwo(gameInput: String): Game {
    val params = gameInput.split(" ")
    val opponent = PlayerInput.valueOf(params[0]).choice
    return Game(
        opponent,
        Outcome.valueOf(params[1]).yourChoice(opponent)
    )
}

data class Game(val opponent: Choice, val player: Choice) {
    fun score(): Int {
        val choiceScore = player.points
        return outcome() + choiceScore
    }

    private fun outcome(): Int {
        return if(player.draw(opponent)) {
            3
        } else if (player.win(opponent)) {
            6
        } else {
            0
        }
    }
}

enum class Outcome {
    X, Y, Z; // WIN, DRAW, LOSE

    fun yourChoice(opponent: Choice): Choice {
        return if (this == Z){
            when(opponent) {
                Choice.ROCK -> Choice.PAPER
                Choice.SCISSORS -> Choice.ROCK
                Choice.PAPER -> Choice.SCISSORS
            }
        } else if (this == X) {
            when(opponent) {
                Choice.ROCK -> Choice.SCISSORS
                Choice.SCISSORS -> Choice.PAPER
                Choice.PAPER -> Choice.ROCK
            }
        } else {
            opponent
        }
    }
}

enum class PlayerInput(val choice: Choice) {
    A(Choice.ROCK),
    B(Choice.PAPER),
    C(Choice.SCISSORS),
    X(Choice.ROCK),
    Y(Choice.PAPER),
    Z(Choice.SCISSORS)
}

enum class Choice(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    fun win(other: Choice): Boolean {
        return when(this) {
            ROCK -> SCISSORS == other
            PAPER -> ROCK == other
            SCISSORS -> PAPER == other
        }
    }

    fun draw(other: Choice): Boolean {
        return this == other
    }
}
