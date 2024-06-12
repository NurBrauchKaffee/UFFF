package ru.justneedcoffee.ufff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class VertebrateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.vertebrate)

        val btnBirds : Button = findViewById(R.id.btnBirds)
        btnBirds.setOnClickListener {
            val intent = Intent(this@VertebrateActivity, ListActivity::class.java)
            intent.putExtra("TYPE", "birds")
            intent.putExtra("QUANTITY", resources.getStringArray(R.array.birds_images).size)
            startActivity(intent)
        }

        val btnFish : Button = findViewById(R.id.btnFish)
        btnFish.setOnClickListener {
            val intent = Intent(this@VertebrateActivity, ListActivity::class.java)
            intent.putExtra("TYPE", "fish")
            intent.putExtra("QUANTITY", resources.getStringArray(R.array.fish_images).size)
            startActivity(intent)
        }
    }
}