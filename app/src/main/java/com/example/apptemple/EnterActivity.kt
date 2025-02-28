package com.example.apptemple

import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.APIServices.UserDataInterface
import com.example.apptemple.DataClasses.LoginData
import com.example.apptemple.Responses.ServerResponse
import com.example.apptemple.Retrofit.RetrofitClient
import com.example.apptemple.databinding.ActivityEnterBinding
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class EnterActivity : AppCompatActivity() {
    //Инициализация биндинга и глобальных переменных для более удобной передачи
    private lateinit var binding: ActivityEnterBinding
    private lateinit var customNotification: CustomNotification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        customNotification = CustomNotification(this)

        binding.enterEnterButton.setOnClickListener {
            enterCheck()
        }

        binding.enterRegisterButton.setOnClickListener {
            goRegister()
        }
    }

    private fun enterCheck() {
        val login = binding.enterLoginEdit.text.toString()
        val password = binding.enterLoginEdit.text.toString()

        if (login.isEmpty() || password.isEmpty()) {
            showNotifications("Все поля должны быть заполнены")
        }

        val loginData = LoginData(login = login, password = password)

        val apiService = RetrofitClient.instance.create(UserDataInterface::class.java)
        apiService.authorizeUser(loginData).enqueue(object : retrofit2.Callback<ServerResponse> {

            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                if(response.isSuccessful) {
                    if(response.code() == 201) {
                        startActivity(Intent(this@EnterActivity, EnterActivity::class.java))
                    } else {
                        showNotifications("Успешный ответ, но код: ${response.code()}")
                    }
                } else {
                    showNotifications("Ошибка авторизации, код: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                showNotifications("Ошибка сети: ${t.message}")
            }
        })
    }

    private fun goRegister() {
        startActivity(Intent(this@EnterActivity, RegisterActivity::class.java))
    }

    private fun showNotifications(message: String) {
        customNotification.showNotification(message)
    }
}