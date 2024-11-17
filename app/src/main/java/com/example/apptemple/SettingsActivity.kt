package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        goBack()
        goExit()
        goTelegram()
        toProfileSettings()
    }

    private fun goTelegram() {
        binding.telegramButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/+4Utaaq2N6Z0wNTEy")))
        }
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
            startActivity(Intent(this, EnterActivity::class.java))
        }
    }

    private fun toProfileSettings() {
        binding.profileSetting.setOnClickListener {
            startActivity(Intent(this, ScheduleActivity::class.java))
        }
    }
}