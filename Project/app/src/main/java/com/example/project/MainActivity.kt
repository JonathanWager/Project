package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.fragmentContainerView6)
        val textNavigate: TextView = findViewById(R.id.textView)

        textNavigate.setOnClickListener {
            navController.navigateUp()
            navController.navigate(R.id.movieSearchActivity)
        }
    }
}