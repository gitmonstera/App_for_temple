package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivitySettingsBinding

class Settings_Activity : AppCompatActivity() {
    private val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        goBack()
        goExit()
        toProfileSettings()
    }

    //При нажатии на кнопку "Назад" просто завершаем текущий активити
    private fun goBack() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    //При нажатии кнопки "Выйти из аккаунта" перезапускаем до активити входа
    private fun goExit() {
        binding.exitButton.setOnClickListener {
            startActivity(Intent(this, Enter_Activity::class.java))
        }
    }

    private fun toProfileSettings() {
        binding.profileSetting.setOnClickListener {
            startActivity(Intent(this, Schedule_Activity::class.java))
        }
    }

}