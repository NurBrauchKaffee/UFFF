@file:Suppress("DEPRECATION")

package ru.justneedcoffee.ufff

import android.app.Dialog
import android.app.DialogFragment
import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.about)

        val btnClrPrg : Button = findViewById(R.id.btnClrPrg)
        btnClrPrg.setOnClickListener {
            val sdf = SureDialogFragment()
            val manager : FragmentManager = fragmentManager
            sdf.show(manager, "sureDialog")
        }
    }
}

class SureDialogFragment : DialogFragment() {
    private val list = listOf (
        "birds",
        "fish",
        "dendro",
        "flowers",
        "water",
        "ground"
    )

    @Deprecated("Deprecated in Java")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.clear_progress)
                .setMessage(R.string.are_you_sure)
                .setCancelable(true)
                .setNegativeButton(R.string.yes) {
                        _, _ ->
                    run {
                        for (s in list) {
                            it.getSharedPreferences(s, Context.MODE_PRIVATE).edit().clear().apply()
                        }
                        it.finish()
                    }
                }
                .setPositiveButton(R.string.no) {
                        dialog, _ -> dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}