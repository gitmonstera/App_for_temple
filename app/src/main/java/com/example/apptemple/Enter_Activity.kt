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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toRegisterActivity()
        enterCheck()
        enterData()
    }

    private fun enterCheck() {
        val intent = Intent(this, App_Activity::class.java)
        binding.enterEnterButton.setOnClickListener {
            //Тут будет проверка данных из БД
            if (binding.enterLoginEdit.text.toString()  == "1" && binding.enterPasswordEdit.text.toString() == "1") {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Введен неверный логин ил пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toRegisterActivity() {
        binding.enterRegisterButton.setOnClickListener {
            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }
    }

    private fun enterData() {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        val userLogin = sharedPreferences.getString("login", "")
        binding.enterLoginEdit.setText(userLogin)
    }
}