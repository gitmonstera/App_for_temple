package com.example.apptemple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.databinding.ActivitySettingsBinding
import com.example.apptemple.utils.navigate

class SettingsActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        goEnter()
        goBack()
    }

    private fun goBack() = with(binding) {
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun goEnter() = with(binding) {
        buttonExit.setOnClickListener {
            navigate(Enter_Form::class.java)
        }
    }
}