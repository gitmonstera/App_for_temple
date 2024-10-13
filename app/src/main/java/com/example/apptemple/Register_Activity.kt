package com.example.apptemple

import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.APIServices.UserDataInterface
import com.example.apptemple.DataClasses.UserData
import com.example.apptemple.Responses.UserResponse
import com.example.apptemple.Retrofit.RetrofitClient
import com.example.apptemple.databinding.ActivityQuestionBinding
import com.example.apptemple.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register_Activity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var customNotification: CustomNotification
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        customNotification = CustomNotification(this)
        toEnterActivity()
        windowCheck()
    }

    private fun toEnterActivity() {
        //При возвращении назад просто завершаем текущий активити
        binding.registerEnterButton.setOnClickListener{
            finish()
        }
    }

    private fun windowCheck() {
        binding.registerRegisterButton.setOnClickListener {
            val userSecondName = binding.userSecondNamelEdit.text.toString()
            val userName = binding.userNameEdit.text.toString()
            val userEmail = binding.registerMailEdit.text.toString()
            val userLogin = binding.registerLoginEdit.text.toString()
            val userPassword = binding.registerPasswordEdit.text.toString()

            if (validateFields(userSecondName, userName,userEmail, userLogin, userPassword)) {
                if (mailCheck(userEmail)) {
                    if (binding.agreeCheckBox.isChecked) {
                        registerUser(userSecondName, userName, userEmail, userLogin, userPassword)
                    } else {
                        binding.agreeCheckBox.setTextColor(getColor(R.color.red))
                        showNotification("Необходимо согласиться с политикой конфиденциальности")
                    }
                } else {
                    showNotification("Неправильная почта")
                }
            }
        }
    }

    private fun validateFields(secondName: String ,firstName: String, email: String, login: String, password: String): Boolean {
        if (email.isEmpty()) {
            showNotification("Введите почту")
            return false
        }
        if (email.isEmpty()) {
            showNotification("Введите фамилию")
            return false
        }
        if (email.isEmpty()) {
            showNotification("Введите имя")
            return false
        }
        if (login.isEmpty()) {
            showNotification("Введите логин")
            return false
        }
        if (password.isEmpty()) {
            showNotification("Введите пароль")
            return false
        }
        return true
    }

    private fun registerUser(secondName: String, firstName: String, email: String, login: String, password: String) {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val passCheck = sharedPreferences.getBoolean("passChecker", false)

        val userData = UserData(last_name = secondName, first_name = firstName ,email = email, username = login, password = password)
        val apiService = RetrofitClient.instance.create(UserDataInterface::class.java)

        apiService.createUser(userData).enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    if (response.code() == 201) {
                        cacheSave(secondName, firstName, email, login, password, passCheck)
                        showNotification("Проверьте почту для завершения регистрации")
                        Thread.sleep(3000)
                        startActivity(Intent(this@Register_Activity, Enter_Activity::class.java))
                    } else {
                        showNotification("Успешный ответ, но код: ${response.code()}")
                    }
                } else {
                    showNotification("Ошибка регистрации, код: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                showNotification("Ошибка сети: ${t.message}")
            }
        })
    }

    private fun cacheSave (secondName: String ,firstName: String,email: String, login: String, password: String, passCheck: Boolean) {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if (passCheck) {
            editor.putString("last_name", secondName)
            editor.putString("first_name", firstName)
            editor.putString("email", email)
            editor.putString("username", login)
            editor.putString("password", password)
            editor.apply()
        }else {
            showNotification("Логин и пароль не сохранены")
        }
    }

    private fun showNotification(message: String) {
        customNotification.showNotification(message)
    }

    private fun mailCheck(mail:String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
}