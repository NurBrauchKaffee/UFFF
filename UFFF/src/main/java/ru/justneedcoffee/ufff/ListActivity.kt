package ru.justneedcoffee.ufff

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private lateinit var prefs: SharedPreferences

class ListActivity : ComponentActivity() {
    private val types = mapOf(
        "birds" to "Птица",
        "fish" to "Рыба",
        "dendro" to "Дерево",
        "flowers" to "Цветок",
        "water" to "Водное",
        "ground" to "Наземное"
    )

    private var quantity : Int = 0
    private lateinit var list : RecyclerView

    private fun initPrefs() {
        val editor = prefs.edit()
        (0..<quantity).forEach { i -> editor.putInt(i.toString(), 0).apply() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.list)

        val intent = intent
        val type = intent.getStringExtra("TYPE").toString()

        prefs = getSharedPreferences(type, Context.MODE_PRIVATE)
        initPrefs()

        quantity = intent.getIntExtra("QUANTITY", 0)
        val titles = mutableListOf<String>()
        (0..<quantity).forEach { i -> titles.add(types[type] + " №" + (i + 1)) }

        list = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.adapter = CustomRecyclerAdapter(titles, type)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        list.adapter?.notifyDataSetChanged()
        super.onResume()
    }
}

class CustomRecyclerAdapter(private val titles: List<String>, private val type: String) :
    RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listItem : TextView = itemView.findViewById(R.id.listItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.listItem.text = titles[position]

        val correct = prefs.getInt(position.toString(), 0)
        when (correct) {
            1 -> {
                holder.listItem.setBackgroundColor(holder.listItem.resources.getColor(R.color.green_400))
            }
            -1 -> {
                holder.listItem.setBackgroundColor(holder.listItem.resources.getColor(R.color.red_400))
            }
        }

        holder.listItem.setOnClickListener {
            val intent = Intent(it.context, QuizActivity::class.java)
            intent.putExtra("TYPE", type)
            intent.putExtra("POSITION", position)

            it.context.startActivity(intent)
        }
    }
}