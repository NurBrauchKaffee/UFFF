package ru.justneedcoffee.ufff

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.android.material.textfield.TextInputEditText

class QuizActivity : ComponentActivity() {
    private val map = mapOf (
        "birds" to Triple(R.array.birds_images, R.array.birds_russian, R.array.birds_latin),
        "fish" to Triple(R.array.fish_images, R.array.fish_russian, R.array.fish_latin),
        "dendro" to Triple(R.array.dendro_images, R.array.dendro_russian, R.array.dendro_latin),
        "flowers" to Triple(R.array.flowers_images, R.array.flowers_russian, R.array.flowers_latin),
        "water" to Triple(R.array.water_images, R.array.water_russian, R.array.water_latin),
        "ground" to Triple(R.array.ground_images, R.array.ground_russian, R.array.ground_latin)
    )

    private fun isCorrectAnswer(answer: String, expectedAnswer: String) : Boolean {
        return answer.equals(expectedAnswer, ignoreCase = true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.quiz)

        val img : ImageView = findViewById(R.id.img)

        val type = intent.getStringExtra("TYPE")
        val position = intent.getIntExtra("POSITION", 0)

        val image = resources.getIdentifier(resources.getStringArray(map[type]!!.first)[position],
            "drawable", packageName)
        img.setImageResource(image)

        val russianAnswer = resources.getStringArray(map[type]!!.second)[position]
        val latinAnswer = resources.getStringArray(map[type]!!.third)[position]

        val debug : TextView = findViewById(R.id.debug)
        //ыdebug.text = "$russianAnswer $latinAnswer"

        val btnSend : Button = findViewById(R.id.btnSend)
        btnSend.setOnClickListener {
            val typeAnswer : TextInputEditText = findViewById(R.id.typeAnswer)

            val ans = typeAnswer.text.toString().trim()
            val duration = Toast.LENGTH_SHORT

            val text : String = if (
                isCorrectAnswer(ans, latinAnswer)
                || isCorrectAnswer(ans, russianAnswer)
                ) {
                "Да, это $ans"
            } else {
                "Нет, это не $ans"
            }

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}