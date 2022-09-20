package com.example.simpemathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    lateinit var resultTextView : TextView
    lateinit var correctAnswer : TextView
    lateinit var scoreTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer2)

        resultTextView = findViewById(R.id.resultView)
        correctAnswer = findViewById(R.id.correctAnswerView)
        scoreTextView = findViewById(R.id.scoreView)

        val button =findViewById<Button>(R.id.backButton)

        val answer = intent.getBooleanExtra("answeredCorrect" , false)
        val rightAnswer = intent.getIntExtra("correctAnswer", 0)
        val rightScore = intent.getIntExtra("score", 0)
        val wrongScore = intent.getIntExtra("wrongScore", 0)
        val total = rightScore + wrongScore
        if (!answer) {

            correctAnswer.text = " Det rätta svaret är $rightAnswer"
            scoreTextView.text = "Du har fått $rightScore rätt och $wrongScore fel" +
                                        " av totalt $total frågor!"
        } else {
            correctAnswer.text = "Bravo du är duktig på Matte fortsätt! "
            scoreTextView.text = "Du har fått $rightScore rätt och $wrongScore fel "
        }

        resultTextView.text = if(answer) {
            "Rätt svar"
        } else {
            "Fel svar"


        }

        button.setOnClickListener {
            //val intent = Intent( this, MainActivity::class.java)
            //startActivity(intent)
            finish()
        }

        //ternary operator : java?
        //resultTextView.text = answer ? "Rätt svar" : "Fel svar"



    }
}