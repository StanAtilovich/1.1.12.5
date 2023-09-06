package ru.stan.a11125.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.stan.a11125.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}