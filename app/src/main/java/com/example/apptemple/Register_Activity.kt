package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivityRegisterBinding

class Register_Activity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toEnterActivity()
        completeRegister()
    }

    private fun toEnterActivity() {
        //При возвращении назад просто завершаем текущий активити
        binding.registerEnterButton.setOnClickListener{
            finish()
        }
    }

    private fun completeRegister() {
        binding.registerRegisterButton.setOnClickListener{
            val userEmail = binding.registerMailEdit.text.toString()
            val userLogin = binding.registerLoginEdit.text.toString()
            val userPassword = binding.registerPasswordEdit.text.toString()
            val intent = Intent(this, Enter_Activity::class.java)

            //Проверка и если пользователь согласился, то сохраняем логин и пароль
            if (binding.agreeCheckBox.isChecked) {
                val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                val passCheck = sharedPreferences.getBoolean("passChecker", false)

                //Проверка флажка "Запомнить меня"
                if (passCheck) {
                    editor.putString("login", userLogin)
                    editor.putString("password", userPassword)
                }else {
                    Toast.makeText(this, "Логин и пароль не сохранен", Toast.LENGTH_SHORT).show()
                }
                editor.apply()

                Toast.makeText(this, "Проверьте E-mail: $userEmail", Toast.LENGTH_SHORT).show()
            }else {
                binding.agreeCheckBox.setTextColor(resources.getColor(R.color.red))
            }

            //Создается новый активити для корректной подстановки значений
            startActivity(intent)
        }
    }
}