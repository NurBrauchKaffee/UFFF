package ru.justneedcoffee.ufff

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.text.toLowerCase
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale
import kotlin.math.exp

class QuizActivity : ComponentActivity() {
    private val map = mapOf (
        "birds" to Triple(R.array.birds_images, R.array.birds_russian, R.array.birds_latin),

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

        val imageName = resources.getStringArray(map[type]!!.first)[position].replace('/', '.').split(".")
        val image = resources.getIdentifier(imageName[imageName.size - 2],
            "drawable", packageName)
        img.setImageResource(image)

        val russianAnswer = resources.getStringArray(map[type]!!.second)[position]
        val latinAnswer = resources.getStringArray(map[type]!!.third)[position]

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