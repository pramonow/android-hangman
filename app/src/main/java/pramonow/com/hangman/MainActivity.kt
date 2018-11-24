package pramonow.com.hangman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonA = findViewById<Button>(R.id.buttonA)
        var buttonB = findViewById<Button>(R.id.buttonB)
        var buttonC = findViewById<Button>(R.id.buttonC)
        var hangman = findViewById<Hangman>(R.id.hangmanLayout)

        hangman.initializeGame("aaabababbba")

        buttonA.setOnClickListener { v ->  hangman.check('a') }
        buttonB.setOnClickListener { v -> hangman.check('b') }
        buttonC.setOnClickListener { v -> hangman.check('c') }

    }
}
