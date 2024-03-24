package com.example.apptemple

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.databinding.ActivityRegisterFormBinding

class Register_Form : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterFormBinding.inflate(layoutInflater) }
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
    }
    // при возвращении назад лучше использовать функцию finish(). Оно закрывает текущее активити, что предотвращяет создание нового активити, которое занимало бы память на устройстве.
    private fun changeWindow() {
        binding.buttonRegisterEnter.setOnClickListener {
            finish()
        }
    }
}