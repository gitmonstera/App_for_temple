package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {
    private val binding by lazy { ActivityQuestionBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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