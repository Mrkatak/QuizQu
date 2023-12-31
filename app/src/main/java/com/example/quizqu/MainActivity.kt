package com.example.quizqu

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizqu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val question = arrayOf(
        "Siapa namamu tuan?",
        "Dimana rumahnya?",
        "Saya Siapa?"
    )

    private val options = arrayOf(
        arrayOf("udin", "budi", "owi"),
        arrayOf("disini", "disana", "dimana ya"),
        arrayOf("asli rill", "up dulu", "kamu siapa")
    )

    private val correctAnswer = arrayOf(2, 0, 2)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()



        binding.btnOption1.setOnClickListener {
            checkAnswer(0)
        }
        binding.btnOption2.setOnClickListener {
            checkAnswer(1)
        }
        binding.btnOption3.setOnClickListener {
            checkAnswer(2)
        }

        binding.btnRestart.setOnClickListener {
            restartQuiz()
        }

    }

    private fun correctBtnColors(buttonIndex: Int) {
        when(buttonIndex){
            0 -> binding.btnOption1.setBackgroundColor(Color.GREEN)
            1 -> binding.btnOption2.setBackgroundColor(Color.GREEN)
            2 -> binding.btnOption3.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongBtnColors(buttonIndex: Int) {
        when(buttonIndex){
            0 -> binding.btnOption1.setBackgroundColor(Color.RED)
            1 -> binding.btnOption2.setBackgroundColor(Color.RED)
            2 -> binding.btnOption3.setBackgroundColor(Color.RED)
        }
    }

    private fun resetBtnColors() {
        binding.btnOption1.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.btnOption2.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.btnOption3.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    private fun showResults() {
        Toast.makeText(this, "Your Score : $score out of ${question.size}", Toast.LENGTH_LONG).show()
        binding.tvScore.text = "Benar $score dari  soal"
        binding.btnRestart.isEnabled = true
    }

    private fun displayQuestion() {
        binding.tvQuestion.text = question[currentQuestionIndex]
        binding.btnOption1.text = options[currentQuestionIndex][0]
        binding.btnOption2.text = options[currentQuestionIndex][1]
        binding.btnOption3.text = options[currentQuestionIndex][2]
        resetBtnColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswer[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctBtnColors(selectedAnswerIndex)
        }else {
            wrongBtnColors(selectedAnswerIndex)
            correctBtnColors(correctAnswerIndex)
        }

        if (currentQuestionIndex < question.size - 1) {
            currentQuestionIndex++
            binding.tvQuestion.postDelayed({displayQuestion()}, 1500)

        }else{
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.btnRestart.isEnabled = false
    }

}