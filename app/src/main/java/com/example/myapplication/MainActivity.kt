package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val generate = findViewById<Button>(R.id.generate)
    val number = findViewById<TextView>(R.id.number)
    val from = findViewById<EditText>(R.id.from)
    val to = findViewById<EditText>(R.id.to)
    val executor = Executors.newSingleThreadScheduledExecutor()
    generate.setOnClickListener {
       val fromValue = if  (from.text.isNotBlank()){
           from.text.toString().toInt()
       } else {
           from.setText("0")
           0
       }
        val toValue = if (to.text.isNotBlank()){
            to.text.toString().toInt()
        } else {
            val random = Random(System.currentTimeMillis()).nextInt( 10,100)
            to.setText(random.toString())
            random
        }

       fun set() {
           val random = Random(System.currentTimeMillis()).nextInt(fromValue, toValue + 1)
           number.text = random.toString()
       }
        for (i in 0 .. 4) {
            executor.schedule(::set, i * 100L, TimeUnit.MILLISECONDS)
    }
       }
    }
}