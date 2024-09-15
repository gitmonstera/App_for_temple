package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivityEnterBinding
import kotlinx.coroutines.CoroutineScope

class Enter_Activity : AppCompatActivity() {
    //Инициализация биндинга и глобальных переменных для более удобной передачи
    private val binding by lazy { ActivityEnterBinding.inflate(layoutInflater) }
    private var userLogin: String? = null
    private var userPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                // Устанавливаем флаг для рисования фона системных панелей
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

                // Делаем навигационную панель прозрачной
                navigationBarColor = Color.TRANSPARENT

                // Оставляем содержимое под системными панелями (статус и навигация)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        enterData()
        enterCheck()
        passwordCheck()
    }

    private fun enterCheck() {
        //Создание исполнителя для переключения активити
        val intent = Intent(this, App_Activity::class.java)

        //При нажатии на кнопку данные логина и пароля проверяются с данными из кэша и либо осуществляется вход, либо выводится сообщение об ошибке
        binding.enterEnterButton.setOnClickListener {
            if (binding.enterLoginEdit.text.toString() == userLogin && binding.enterPasswordEdit.text.toString() == userPassword) {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Введен неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun enterData() {
        //Переменная для ввода и вывода данных из кэша
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        userLogin = sharedPreferences.getString("login", "")
        userPassword = sharedPreferences.getString("password", "")
        val passChecker = sharedPreferences.getBoolean("passChecker", false)

        //Галочка "Запомнить меня" привязана к флажку и в зависимости от первоначального выбора будет вкл/выкл
        binding.passwordCheckbox.isChecked = passChecker

        //Если разрешение есть, то логин и пароль выводятся в поля
        if (passChecker) {
            binding.enterLoginEdit.setText(userLogin)
            binding.enterPasswordEdit.setText(userPassword)
        }
    }

    private fun passwordCheck() {
        //Переменная для ввода и вывода данных из кэша
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        //При нажатии на кнопку "Зарегистрироваться" состояние флажка сохраняется в кэш
        binding.enterRegisterButton.setOnClickListener {
            val passChecker = binding.passwordCheckbox.isChecked
            editor.putBoolean("passChecker", passChecker)
            editor.apply()

            //Инициализация исполнителя и его запуск(переход на активити регистрации)
            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }
    }
}
