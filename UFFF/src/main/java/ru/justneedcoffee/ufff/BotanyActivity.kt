package ru.justneedcoffee.ufff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class BotanyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.botany)

        val btnDendrology : Button = findViewById(R.id.btnDendrology)
        btnDendrology.setOnClickListener {
            val intent = Intent(this@BotanyActivity, ListActivity::class.java)
            intent.putExtra("TYPE", "dendro")
            intent.putExtra("QUANTITY", resources.getStringArray(R.array.dendro_images).size)
            startActivity(intent)
        }

        val btnFlowers : Button = findViewById(R.id.btnFlowers)
        btnFlowers.setOnClickListener {
            val intent = Intent(this@BotanyActivity, ListActivity::class.java)
            intent.putExtra("TYPE", "flowers")
            intent.putExtra("QUANTITY", resources.getStringArray(R.array.flowers_images).size)
            startActivity(intent)
        }
    }
}