package pramonow.com.hangman

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.Toast

class Hangman: ConstraintLayout {

    //Message list for game status value
    private val GAME_OVER_LOSE = "GAME_OVER_LOSE"
    private val GAME_NOT_FINISHED = "GAME_NOT_FINISHED"
    private val GAME_OVER_WIN = "GAME_OVER_WIN"
    private val GAME_NOT_STARTED = "GAME_NOT_STARTED"

    //message
    private val GAME_OVER_MESSAGE = "GAME_OVER"
    private val GAME_WIN_MESSAGE = "CONGRATULATIONS"

    private var MAX_ANSWER = 10

    //number of wrong answer
    private var strike:Int = 0
    private var answer:String = ""
    private var lines: MutableList<View> = ArrayList()
    private lateinit var guess: CharArray

    //initialise game status as not started
    private var gameStatus = GAME_NOT_STARTED

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet) {

        View.inflate(context, R.layout.hangman,this)

        //adding each line one by one
        this.lines.add(findViewById(R.id.first_line))
        this.lines.add(findViewById(R.id.second_line))
        this.lines.add(findViewById(R.id.third_line))
        this.lines.add(findViewById(R.id.fourth_line))
        this.lines.add(findViewById(R.id.head))
        this.lines.add(findViewById(R.id.fifth_line))
        this.lines.add(findViewById(R.id.sixth_line))
        this.lines.add(findViewById(R.id.seventh_line))
        this.lines.add(findViewById(R.id.eight_line))
        this.lines.add(findViewById(R.id.ninth_line))
    }

    //initialise or start a new game
    fun initializeGame(answer:String) {
        this.answer = answer
        this.guess = CharArray(answer.length){'_'}
        this.gameStatus = GAME_NOT_FINISHED

        lines.forEach{
            it.visibility = View.INVISIBLE
        }
    }

    //Main logic for checking answer
    fun check(char:Char) {
        //If game is over (either number of wrong answer more than 10 or correct answer or answer already guessed)
        if(gameStatus == GAME_OVER_WIN || gameStatus == GAME_OVER_LOSE) {
            Toast.makeText(context, GAME_OVER_MESSAGE, Toast.LENGTH_SHORT).show()
            return
        }

        //If wrong answer
        if(checkGuess(char) == false) {
            addStrike()

            //If game is over
            if(strike == MAX_ANSWER) {
                gameStatus = GAME_OVER_LOSE
                Toast.makeText(context, GAME_OVER_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }
        else {
            fillGuess(char)

            //if answer is all correct
            if(answer == guess.joinToString("")) {
                gameStatus = GAME_OVER_WIN
                Toast.makeText(context, GAME_WIN_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }

        //if haven't finished then toast out current answer
        if(gameStatus == GAME_NOT_FINISHED) {
            Toast.makeText(context,guess.joinToString(""), Toast.LENGTH_SHORT).show()
        }
    }

    fun getGameStatus():String {
        return gameStatus
    }

    fun getCurrentAnswer():String {
        return guess.joinToString("")
    }

    //check if corresponding character is correct
    private fun checkGuess(char: Char):Boolean {

        if(answer.contains(char,true))
            return true;

        return false;
    }

    //when wrong answer then add line in the hangman
    private fun addStrike() {
        lines[strike].visibility = View.VISIBLE
        strike++;
    }

    //fill the answer with the char
    private fun fillGuess(char:Char) {
        answer.forEachIndexed { index, c ->
            if(c.equals(char)) guess[index] = c }
    }

}