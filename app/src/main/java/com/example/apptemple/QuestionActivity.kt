package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sendRequest()
        goBack()
    }

    private fun sendRequest() {
        binding.sendRequestButton.setOnClickListener {
            finish()
            Toast.makeText(this, "Ваш запрос успешно отправлен", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goBack() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}