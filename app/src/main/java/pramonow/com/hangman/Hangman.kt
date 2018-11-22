package pramonow.com.hangman

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.Toast

class Hangman: ConstraintLayout {

    var strike:Int = 0
    var answer:String = ""
    lateinit var guess: CharArray

    var lines: MutableList<View> = ArrayList()

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet) {

        View.inflate(context, R.layout.hangman,this)

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

    fun initializeGame(answer:String)
    {
        this.answer = answer
        this.guess = CharArray(answer.length){'_'}

        lines.forEach{
            it.visibility = View.INVISIBLE
        }
    }

    fun checkAnswer(char: Char)
    {
        var correct = false
        answer.forEachIndexed { index, c -> if(c.equals(char)) {guess[index] = c; correct = true} }

        if(answer == guess.joinToString{""}) {
            Toast.makeText(context, "CORRECT", Toast.LENGTH_SHORT).show()
            return
        }

        if(correct == false)
        {
            lines[strike].visibility = View.VISIBLE
            strike++;

            if(strike == 10)
            {
                Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT).show()
                return
            }

            Toast.makeText(context, "WRONG", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(context, guess.joinToString {""}, Toast.LENGTH_SHORT).show()
        }
    }





}