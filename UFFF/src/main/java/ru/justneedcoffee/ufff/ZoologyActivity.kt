package ru.justneedcoffee.ufff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class ZoologyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.zoology)

        val btnVertebrate : Button = findViewById(R.id.btnVertebrate)
        btnVertebrate.setOnClickListener {
            val intent = Intent(this@ZoologyActivity, VertebrateActivity::class.java)
            startActivity(intent)
        }

        val btnInvertebrate : Button = findViewById(R.id.btnInvertebrate)
        btnInvertebrate.setOnClickListener {
            val intent = Intent(this@ZoologyActivity, InvertebrateActivity::class.java)
            startActivity(intent)
        }
    }
}