package com.example.apptemple

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
        binding.registerEnterButton.setOnClickListener{
            finish()
        }
    }

    private fun completeRegister() {
        binding.registerRegisterButton.setOnClickListener{
            val userEmail = binding.registerMailEdit.text.toString()
            val userLogin = binding.registerLoginEdit.text.toString()
            val userPassword = binding.registerPasswordEdit.text.toString()

            if (binding.agreeCheckBox.isChecked) {
                val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("login", userLogin)

                //Здесь сделать булеву переменную для сохренения пароля
                editor.putString("password", userPassword)
                editor.apply()

                Toast.makeText(this, "Проверьте E-mail: ${userEmail}", Toast.LENGTH_SHORT).show()
            }else {
                binding.agreeCheckBox.setTextColor(resources.getColor(R.color.red))
            }
        }
    }
}