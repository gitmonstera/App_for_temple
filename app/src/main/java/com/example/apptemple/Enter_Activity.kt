package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivityEnterBinding

class Enter_Activity : AppCompatActivity() {
    //Инициализация биндинга и глобальных переменных для более удобной передачи
    private lateinit var binding : ActivityEnterBinding
    private lateinit var customNotification: CustomNotification
    private var userLogin: String? = null
    private var userPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        customNotification = CustomNotification(this)
        enterData()
        enterCheck()
        passwordCheck()
    }

    private fun enterCheck() {
        //При нажатии на кнопку данные логина и пароля проверяются с данными из кэша и либо осуществляется вход, либо выводится сообщение об ошибке
        binding.enterEnterButton.setOnClickListener {
            if (binding.enterLoginEdit.text.toString() == userLogin && binding.enterPasswordEdit.text.toString() == userPassword) {
                startActivity(Intent(this, App_Activity::class.java))
            }
            else {
                showNotifications("Введен неверный логин или пароль")
            }
        }
    }

    private fun enterData() {
        //Переменная для ввода и вывода данных из кэша
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        userLogin = sharedPreferences.getString("username", "")
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

        binding.passwordCheckbox.setOnCheckedChangeListener { _, _ ->
            val passChecker = binding.passwordCheckbox.isChecked
            editor.putBoolean("passChecker", passChecker)
            editor.apply()
        }


        //При нажатии на кнопку "Зарегистрироваться" состояние флажка сохраняется в кэш
        binding.enterRegisterButton.setOnClickListener {
            //Инициализация исполнителя и его запуск(переход на активити регистрации)
            startActivity(Intent(this, Register_Activity::class.java))
        }
    }

    private fun showNotifications(message: String) {
        customNotification.showNotification(message)
    }
}
