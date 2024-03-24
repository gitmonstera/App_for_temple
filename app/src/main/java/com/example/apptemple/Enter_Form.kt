package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivityEnterFormBinding

class Enter_Form : AppCompatActivity() {
    private val binding by lazy { ActivityEnterFormBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        changeWindow()
        enterTheApp()
    }

    private fun changeWindow(){
        binding.buttonEnterRegister.setOnClickListener {
            val intent = Intent(this, Register_Form::class.java)
            startActivity(intent)
        }
    }

    private fun enterTheApp() {
        val enteredLogin = binding.editTextEnterLogin.text.toString()
        val enteredPassword = binding.editTextEnterPassword.text.toString()

        binding.buttonEnterEnter.setOnClickListener {
            //Тут условие для проверки вводимых в поля данных
        }
    }
}