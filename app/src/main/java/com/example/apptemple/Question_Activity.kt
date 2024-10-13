package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivityQuestionBinding

class Question_Activity : AppCompatActivity() {
    private lateinit var binding : ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        goBack()
    }

    private fun goBack() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}