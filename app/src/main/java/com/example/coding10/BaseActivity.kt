package com.example.coding10

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showtoast(inputText: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.toast_msg, findViewById(R.id.toast_layout))
        val text = layout.findViewById<TextView>(R.id.tm_text)
        text.text = inputText
        with(Toast(applicationContext)) {
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}