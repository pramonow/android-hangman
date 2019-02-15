package pramonow.com.hangman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialise 3 buttons for hangman
        var buttonA = findViewById<Button>(R.id.buttonA)
        var buttonB = findViewById<Button>(R.id.buttonB)
        var buttonC = findViewById<Button>(R.id.buttonC)

        //intialise hangman
        var hangman = findViewById<Hangman>(R.id.hangmanLayout)

        //init hangman game with dummy answer
        hangman.initializeGame("aaabababbba")

        //set callback for 3 buttons
        buttonA.setOnClickListener { v ->  hangman.check('a') }
        buttonB.setOnClickListener { v -> hangman.check('b') }
        buttonC.setOnClickListener { v -> hangman.check('c') }

    }
}
