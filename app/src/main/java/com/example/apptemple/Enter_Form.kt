package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Enter_Form : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val login = "1"
        val pass = "1"

        val userLogin: EditText = findViewById(R.id.editText_Enter_Login)
        val userPassword: EditText = findViewById(R.id.editText_Enter_Password)
        val userEnter: Button = findViewById(R.id.button_Enter_Enter)

        changeWindow()

        userEnter.setOnClickListener{
            checkEnter(userLogin, userPassword, login, pass)
        }
    }

    fun changeWindow(){
        val toRegister: Button = findViewById(R.id.button_Enter_Register)

        toRegister.setOnClickListener{
            val intent = Intent(this, Register_Form::class.java)
            startActivity(intent)
        }
    }

    fun checkEnter(userLogin: EditText, userPassword: EditText, login: String, pass: String){
        val enteredLogin = userLogin.text.toString()
        val enteredPassword = userPassword.text.toString()

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
                userLogin.text.clear()
                userPassword.text.clear()
            }
        }
    }
}