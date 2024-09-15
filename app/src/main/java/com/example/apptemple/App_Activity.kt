package com.example.apptemple

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.apptemple.databinding.ActivityAppBinding
import androidx.appcompat.app.AppCompatDelegate

class App_Activity : AppCompatActivity() {
    private val binding by lazy { ActivityAppBinding.inflate(layoutInflater) }
    private var pressedTime: Long = 0   // Переменная для считывания длины нажатия
    private lateinit var toast: Toast   // Переменная для вывода тостов (простые сообщения)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        loadFragment(HomeFragment())
        fragmentChanger()
        toSettingsActivity()
        toQuestionActivity()
        toScheduleActivity()
    }

    // Переключатель фрагментов для Боттом бара
    private fun fragmentChanger() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem -> {
                    loadFragment(HomeFragment())
                    binding.frameName.text = "Главная"
                    true
                }

                R.id.lessonsItem -> {
                    loadFragment(LessonsFragment())
                    binding.frameName.text = "Секции"
                    true
                }

                R.id.profileItem -> {
                    loadFragment(ProfileFragment())
                    binding.frameName.text = "Профиль"
                    true
                }
                else -> false
            }
        }
    }

    // Функция для смены фрагментов (Инициализируется транзакция фрагментов, которая и осуществляет переключение фрагментов)
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.appFragmentContainer, fragment)
        transaction.commit()
    }

    // При нажатии на кнопку "Настройки" инициализируется исполнитель и переключает на соответствующий активити
    private fun toSettingsActivity() {
        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, Settings_Activity::class.java))
        }
    }

    // При нажатии на кнопку осуществляется переход на активити "Вопрос-ответ"
    private fun toQuestionActivity() {
        binding.questionButton.setOnClickListener {
            startActivity(Intent(this, Question_Activity::class.java))
        }
    }

    private fun toScheduleActivity() {
        binding.scheduleButton.setOnClickListener {
            startActivity(Intent(this, Schedule_Activity::class.java))
        }
    }

    // Функция для игнорирования вызова "super"
    @SuppressLint("MissingSuperCall")

    // Функция для подтверждения выхода двойным нажатием кнопки "Назад"
    override fun onBackPressed() {
        // Переменная отсчитывающая текущее время
        val currentTime = System.currentTimeMillis()

        // Если от момента нажатия прошло менее 2 секунд, то осуществляется выход из приложения
        if (currentTime - pressedTime < 2000) {
            toast.cancel()
            super.finishAffinity()
            return
        } else {
            // Если от момента нажатия прошло более 2 секунд, то выводится сообщение
            toast = Toast.makeText(this, "Нажмите ещё раз для выхода", Toast.LENGTH_SHORT)
            toast.show()
        }
        pressedTime = currentTime
    }
}