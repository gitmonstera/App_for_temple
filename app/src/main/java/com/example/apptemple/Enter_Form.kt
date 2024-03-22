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
        val userLogin: EditText = findViewById(R.id.editText_Enter_Login)
        val userPassword: EditText = findViewById(R.id.editText_Enter_Password)
        val buttonEnter: Button = findViewById(R.id.button_Enter_Enter)

        changeWindow()
        enterTheApp(userLogin, userPassword, buttonEnter)
    }

    fun changeWindow(){
        val toReg: Button = findViewById(R.id.button_Enter_Register)

        toReg.setOnClickListener{
            val intent = Intent(this, Register_Form::class.java)
            startActivity(intent)
        }
    }

    fun enterTheApp(userLogin: EditText, userPassword: EditText, buttonEnter: Button){
        val enteredLogin = userLogin.text.toString()
        val enteredPassword = userPassword.text.toString()

        buttonEnter.setOnClickListener{
            //Тут условие для проверки вводимых в поля данных
        }
    }
}