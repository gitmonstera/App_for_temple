package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivitySettingsBinding

class Settings_Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

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