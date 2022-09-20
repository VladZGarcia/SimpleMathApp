package com.example.simpemathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var questionTextView : TextView
    lateinit var answerView : EditText
    var correctAnswer : Int = 0
    var score : Int =0
    var wrongScore : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        answerView = findViewById(R.id.answerView)

        val button =findViewById<Button>(R.id.answerButton)

        button.setOnClickListener {
          handleButtonPress()
        }

        setNewQuestion()
    }
    override fun onRestart() {
        super.onRestart()

        answerView.setText("")
        setNewQuestion()

    }

    fun handleButtonPress() {

        val answeredCorrect = checkAnswer()
        if (answeredCorrect) {
            score++
        } else {
            wrongScore++
        }
       Log.d("!!!", "Du Svarade  $answeredCorrect")

        val intent = Intent( this, AnswerActivity::class.java)
        intent.putExtra("answeredCorrect", answeredCorrect)
        intent.putExtra("correctAnswer", correctAnswer)
        intent.putExtra("score", score)
        intent.putExtra("wrongScore", wrongScore)
        startActivity(intent)

    }

    fun checkAnswer() : Boolean {
        // kolla vad användaren svarar
        val answerText = answerView.text.toString()
        val answer = answerText.toIntOrNull()

        // jämför användarens svar med raätta svaret och returnera
        return answer == correctAnswer

    }

    fun setNewQuestion() {
        var calMeth = (1..4).random()
        var firstNumber = (1..10).random()
        var secondNumber = (1..10).random()

        if(calMeth == 1) {
            correctAnswer = firstNumber + secondNumber
            questionTextView.text = "$firstNumber + $secondNumber"
        }
        if(calMeth == 2) {
            correctAnswer = firstNumber * secondNumber
            questionTextView.text = "$firstNumber * $secondNumber"
        }
        if(calMeth == 3) {
            correctAnswer = firstNumber - secondNumber
            questionTextView.text = "$firstNumber - $secondNumber"
        }
        if(calMeth == 4) {
            firstNumber = (1..100).random()
            secondNumber = (1..100).random()
            while(firstNumber % secondNumber != 0) {
                firstNumber = (1..100).random()
                secondNumber = (1..100).random()
            }
                if(firstNumber % secondNumber == 0){
                    correctAnswer = firstNumber / secondNumber
                    questionTextView.text = "$firstNumber / $secondNumber"

                }

            }
    }
}