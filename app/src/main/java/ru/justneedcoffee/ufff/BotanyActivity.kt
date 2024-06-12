package ru.justneedcoffee.ufff

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class BotanyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.botany)

        val dendroTitles = arrayOfNulls<String>(10)
        for (i in 0..9) {
            dendroTitles[i] = "Дерево №" + (i + 1)
        }

        val btnDendrology : Button = findViewById(R.id.btnDendrology)
        btnDendrology.setOnClickListener {
            val intent = Intent(this@BotanyActivity, ListActivity::class.java)
            intent.putExtra("LIST", dendroTitles)
            startActivity(intent)
        }

        val flowerTitles = arrayOfNulls<String>(10)
        for (i in 0..9) {
            flowerTitles[i] = "Цветок №" + (i + 1)
        }

        val btnFlowers : Button = findViewById(R.id.btnFlowers)
        btnFlowers.setOnClickListener {
            val intent = Intent(this@BotanyActivity, ListActivity::class.java)
            intent.putExtra("LIST", flowerTitles)
            startActivity(intent)
        }
    }
}