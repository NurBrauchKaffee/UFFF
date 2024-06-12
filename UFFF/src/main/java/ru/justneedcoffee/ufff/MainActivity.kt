package ru.justneedcoffee.ufff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.main)

        val btnInfo : ImageButton = findViewById(R.id.btnInfo)
        btnInfo.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }

        val btnBotany : Button = findViewById(R.id.btnBotany)
        btnBotany.setOnClickListener {
            val intent = Intent(this@MainActivity, BotanyActivity::class.java)
            startActivity(intent)
        }

        val btnZoology : Button = findViewById(R.id.btnZoology)
        btnZoology.setOnClickListener{
            val intent = Intent(this@MainActivity, ZoologyActivity::class.java)
            startActivity(intent)
        }
    }
}