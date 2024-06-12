package ru.justneedcoffee.ufff

import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
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

    private fun onTypedText(v: View?, typedText: String, russianAnswer: String, latinAnswer: String) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (v != null) {
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }

        val ans = typedText.trim()
        val duration = Toast.LENGTH_SHORT

        val text : String = if (isCorrectAnswer(ans, latinAnswer)
            || isCorrectAnswer(ans, russianAnswer)) {
            "Да, это $ans"
        } else {
            "Нет, это не $ans"
        }

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
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

        val typeAnswer : TextInputEditText = findViewById(R.id.typeAnswer)
        typeAnswer.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER) {

                    typeAnswer.clearFocus()
                    typeAnswer.isCursorVisible = false
                    onTypedText(v, typeAnswer.text.toString(), russianAnswer, latinAnswer)

                    return true
                }
                return false
            }
        })

        val btnSend : Button = findViewById(R.id.btnSend)
        btnSend.setOnClickListener {
            onTypedText(btnSend, typeAnswer.text.toString(), russianAnswer, latinAnswer)
        }
    }
}