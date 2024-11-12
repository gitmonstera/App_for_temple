package com.example.apptemple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.databinding.ActivityScheduleBinding

class ScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
    }

    private fun goBack() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}