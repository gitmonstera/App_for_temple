package com.example.apptemple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.databinding.ActivityScheduleBinding

class ScheduleActivity : AppCompatActivity() {
    private val binding by lazy { ActivityScheduleBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        goBack()
    }

    private fun goBack() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}