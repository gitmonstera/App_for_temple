package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
        registerAccount()
    }
    private fun changeWindow() {
        binding.buttonRegisterEnter.setOnClickListener {
            val intent = Intent(this, Enter_Form::class.java)
            finish()
        }
    }

    private fun registerAccount(){
        binding.buttonRegisterRegister.setOnClickListener {
            if (binding.editTextRegisterLogin.text.toString().trim() != "" && binding.editTextRegisterPassword.text.toString().trim() !== "" && binding.editTextTextEmailAddress.text.toString().trim() !== ""){
                //Добавить код для записи пользователя

                //Тут высвечивается уведомление об успехе
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Успех")
                builder.setMessage("Новый пользователь зарегистрирован")
                builder.setPositiveButton("ok"){ dialog, _ ->
                    dialog.dismiss()
                }
                    .show()
            }
            else{
                Toast.makeText(this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}