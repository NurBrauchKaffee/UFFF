package ru.justneedcoffee.ufff

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class InvertebrateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.invertebrate)

        val btnWater : Button = findViewById(R.id.btnWater)
        btnWater.setOnClickListener {
            val intent = Intent(this@InvertebrateActivity, ListActivity::class.java)
            intent.putExtra("TYPE", "water")
            intent.putExtra("QUANTITY", resources.getStringArray(R.array.water_images).size)
            startActivity(intent)
        }

        val btnGround : Button = findViewById(R.id.btnGround)
        btnGround.setOnClickListener {
            val intent = Intent(this@InvertebrateActivity, ListActivity::class.java)
            intent.putExtra("TYPE", "ground")
            intent.putExtra("QUANTITY", resources.getStringArray(R.array.ground_images).size)
            startActivity(intent)
        }
    }
}