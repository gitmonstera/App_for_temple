package com.example.apptemple

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivityEnterBinding
import kotlinx.coroutines.CoroutineScope

class Enter_Activity : AppCompatActivity() {
    private val binding by lazy { ActivityEnterBinding.inflate(layoutInflater) }
    private var userLogin: String? = null
    private var userPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
        val intent = Intent(this, App_Activity::class.java)
        binding.enterEnterButton.setOnClickListener {
            //Проверка данных с сохраненными
            if (binding.enterLoginEdit.text.toString() == userLogin && binding.enterPasswordEdit.text.toString() == userPassword) {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Введен неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun enterData() {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        userLogin = sharedPreferences.getString("login", "")
        binding.enterLoginEdit.setText(userLogin)
        userPassword = sharedPreferences.getString("password", "")
        binding.enterPasswordEdit.setText(userPassword)
    }

    private fun passwordCheck() {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.enterRegisterButton.setOnClickListener {
            val passChecker = binding.passwordCheckbox.isChecked
            editor.putBoolean("passChecker", passChecker)
            editor.apply()

            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }
    }
}
