package ru.justneedcoffee.ufff

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class InvertebrateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.invertebrate)

        val waterTitles = arrayOfNulls<String>(5)
        for (i in 0..4) {
            waterTitles[i] = "Водное №" + (i + 1)
        }

        val btnWater : Button = findViewById(R.id.btnWater)
        btnWater.setOnClickListener {
            val intent = Intent(this@InvertebrateActivity, ListActivity::class.java)
            intent.putExtra("LIST", waterTitles)
            startActivity(intent)
        }

        val groundTitles = arrayOfNulls<String>(9)
        for (i in 0..8) {
            groundTitles[i] = "Наземное №" + (i + 1)
        }

        val btnGround : Button = findViewById(R.id.btnGround)
        btnGround.setOnClickListener {
            val intent = Intent(this@InvertebrateActivity, ListActivity::class.java)
            intent.putExtra("LIST", groundTitles)
            startActivity(intent)
        }
    }
}