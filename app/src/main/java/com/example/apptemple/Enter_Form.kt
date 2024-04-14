package com.example.apptemple

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.data.database.SchoolDb
import com.example.apptemple.databinding.ActivityEnterFormBinding
import com.example.apptemple.domain.checkUserData
import com.example.apptemple.utils.*
import org.koin.android.ext.android.get

class Enter_Form : AppCompatActivity() {
    private val binding by lazy { ActivityEnterFormBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        changeWindow()
        checkEnter()
    }
    private fun changeWindow() = with(binding) {
        buttonEnterRegister.setOnClickListener {
            navigate(Register_Form::class.java)
        }
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выход")
            .setMessage("Вы уверены, что хотите выйти из приложения?")
            .setPositiveButton("Да") { _, _ ->
                finishAffinity() // Завершение всех активностей в приложении
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss() // Отмена выхода
            }
            .show()
    }

    private fun checkEnter() = with(binding) {
        buttonEnterEnter.setOnClickListener {
            val login = editTextEnterLogin.text.toString()
            val password = editTextEnterPassword.text.toString()
            val message = checkUserData(login, password)
            //если введены некорректные данные, то выводим сообщение
            if(message.isNotBlank()) {
                displayMessage(message)
                return@setOnClickListener
            }
            get<SchoolDb>()
                .userDao()
                .getAllData()
                .find { it.username == login && it.password == password }
                ?.let {// внутри скобок it это данные пользователя
                    val intent = Intent(this@Enter_Form, News_Feed::class.java).apply { putExtra("userName", it.username) }
                    startActivity(intent)
                } ?: displayMessage(Messages.USER_NOT_FOUND)// если пользователь не найден, то выводим сообщение
        }
    }
}