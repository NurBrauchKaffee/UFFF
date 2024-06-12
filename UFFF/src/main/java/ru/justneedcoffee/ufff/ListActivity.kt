package ru.justneedcoffee.ufff

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.ComponentActivity

class ListActivity : ComponentActivity() {
    private val types = mapOf(
        "birds" to "Птица",
        "fish" to "Рыба",
        "dendro" to "Дерево",
        "flowers" to "Цветок",
        "water" to "Водное",
        "ground" to "Наземное"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.list)

        val intent = intent
        val type = intent.getStringExtra("TYPE")
        val quantity = intent.getIntExtra("QUANTITY", 0)

        val titles = arrayOfNulls<String>(quantity)
        for (i in 0..<quantity) {
            titles[i] = types[type] + " №" + (i + 1)
        }

        val list : ListView = findViewById(R.id.list)
        list.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
        list.isTextFilterEnabled = true

        list.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                intent.setClass(this@ListActivity, QuizActivity::class.java)
                intent.putExtra("TYPE", type)
                intent.putExtra("POSITION", position)
                startActivity(intent)
            }
    }
}