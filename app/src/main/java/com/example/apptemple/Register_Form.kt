package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.data.database.SchoolDb
import com.example.apptemple.data.database.User
import com.example.apptemple.databinding.ActivityRegisterFormBinding
import com.example.apptemple.domain.checkUserData
import com.example.apptemple.utils.*
import org.koin.android.ext.android.get

class Register_Form : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterFormBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonRegisterEnter.setOnClickListener { finish() }
        registerAccount()
    }
    private fun registerAccount() = with(binding) {
        buttonRegisterRegister.setOnClickListener {
            val login = editTextRegisterLogin.text.toString()
            val password = editTextRegisterPassword.text.toString()
            val email = editTextTextEmailAddress.text.toString()
            val birthday = editTextAge.text.toString()
            val message = checkUserData(login, password, email, birthday)
            if (message.isNotBlank()) {
                displayMessage(message)
                return@setOnClickListener
            }
            //Добавить код для записи пользователя
            get<SchoolDb>().userDao()//получаем доступ к DAO, который позволяет работать с sqlite
                .getAllData()//получаем всех пользователей
                .find { it.username == login }//ищем пользователя по логину
                ?.let { displayMessage(Messages.USER_ALREADY_EXISTS) }// если пользователь с таким логином уже существует, то выводим сообщение
                ?: get<SchoolDb>()
                    .userDao()
                    .insert(getUserData())// если пользователь с таким логином не существует, то регистрируем его
                    .also {
                        //Тут высвечивается уведомление об успехе
                        val builder = AlertDialog.Builder(this@Register_Form)
                        builder.setTitle("Успех")
                        builder.setMessage("Новый пользователь зарегистрирован")
                        builder.setPositiveButton("ok") { _, _ ->
                            val intent = Intent(this@Register_Form, News_Feed::class.java).apply { putExtra("userName", login) }//передаем имя пользователя в интент с помощью putExtra
                            startActivity(intent)
                        }.show()
                    }
        }
    }
    private fun getUserData(): User = User(
        username = binding.editTextRegisterLogin.text.toString(),
        password = binding.editTextRegisterPassword.text.toString(),
        email = binding.editTextTextEmailAddress.text.toString(),
        name = "qwertyName",//нужно текстовое поле
        surname = "qwertySurname",//нужно текстовое поле
        birthDate = binding.editTextAge.text.toString(),
        role = "qwertyRole",//надо будет поговорить с бэками по этому поводу
        lastActive = "12.05.2024"//LocalDate.now().toString() - требует библиотеку java.time, которая доступна только с 8 версии (API 26) андроида
    )
}