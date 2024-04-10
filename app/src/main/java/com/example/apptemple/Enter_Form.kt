package com.example.apptemple

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
//        val login = "1"
//        val pass = "1"
        changeWindow()

        binding.buttonEnterEnter.setOnClickListener {
            checkEnter()
        }
    }

    private fun changeWindow(){
        binding.buttonEnterRegister.setOnClickListener{
            val intent = Intent(this, Register_Form::class.java)
            startActivity(intent)
        }
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выход")
            .setMessage("Вы уверены, что хотите выйти из приложения?")
            .setPositiveButton("Да") { dialog, which ->
                finishAffinity() // Завершение всех активностей в приложении
            }
            .setNegativeButton("Нет") { dialog, which ->
                dialog.dismiss() // Отмена выхода
            }
            .show()
    }

    private fun checkEnter(login: String = "1", pass: String = "1"){
        val enteredLogin = binding.editTextEnterLogin.text.toString()
        val enteredPassword = binding.editTextEnterPassword.text.toString()

        if (enteredLogin == "" || enteredPassword == ""){
            Toast.makeText(this, "Нужно заполннить все поля", Toast.LENGTH_LONG).show()
        }
        else if (enteredLogin != "" || enteredPassword != ""){
            if (enteredLogin == login && enteredPassword == pass){
                val intent = Intent(this, News_Feed::class.java)
                startActivity(intent)
            }
            else if (enteredLogin != login && enteredPassword != pass){
                Toast.makeText(this, "Вы ввели неверные данные!", Toast.LENGTH_LONG).show()
                binding.editTextEnterLogin.text.clear()
                binding.editTextEnterPassword.text.clear()
            }
        }
    }
}