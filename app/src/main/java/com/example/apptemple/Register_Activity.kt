package com.example.apptemple

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptemple.APIServices.UserDataInterface
import com.example.apptemple.DataClasses.UserData
import com.example.apptemple.Responses.UserResponse
import com.example.apptemple.Retrofit.RetrofitClient
import com.example.apptemple.databinding.ActivityRegisterBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import okhttp3.internal.concurrent.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register_Activity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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
            val userEmail = binding.registerMailEdit.text.toString()
            val userLogin = binding.registerLoginEdit.text.toString()
            val userPassword = binding.registerPasswordEdit.text.toString()

            val dialog = Dialog(this)
            val view = layoutInflater.inflate(R.layout.bottomsheet, null)
            dialog.setContentView(view)

            // Устанавливаем параметры окна
            val window = dialog.window
            window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.TOP) // Показываем сверху

            // Устанавливаем анимацию для окна
            window?.attributes?.windowAnimations = R.style.DialogAnimation // Указываем стиль анимации

            dialog.setCancelable(false)
            dialog.show()

            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
            }, 3000)

            // Validate fields and proceed
            if (validateFields(userEmail, userLogin, userPassword)) {
                if (mailCheck(userEmail)) {
                    if (binding.agreeCheckBox.isChecked) {
                        registerUser(userEmail, userLogin, userPassword)
                    } else {
                        binding.agreeCheckBox.setTextColor(getColor(R.color.red))
                        showMessage("Необходимо согласиться с политикой конфиденциальности")
                    }
                } else {
                    showMessage("Неправильная почта")
                }
            }
        }
    }

    private fun validateFields(email: String, login: String, password: String): Boolean {
        if (email.isEmpty()) {
            showMessage("Введите почту")
            return false
        }
        if (login.isEmpty()) {
            showMessage("Введите логин")
            return false
        }
        if (password.isEmpty()) {
            showMessage("Введите пароль")
            return false
        }
        return true
    }

    private fun registerUser(email: String, login: String, password: String) {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val passCheck = sharedPreferences.getBoolean("passChecker", false)

        val userData = UserData(email = email, login = login, password = password)
        val apiService = RetrofitClient.instance.create(UserDataInterface::class.java)

        apiService.createUser(userData).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    cacheSave(login, password, passCheck)
                    showMessage("Регистрация завершена")
                    startActivity(Intent(this@Register_Activity, Enter_Activity::class.java))
                }else {
                    showMessage("Ошибка регистрации: ${response.body()?.message ?: "Неизвестная ошибка"}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                showMessage("Ошибка сети: ${t.message}")
            }
        })
    }

    private fun cacheSave(login: String, password: String, passCheck: Boolean) {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if (passCheck) {
            editor.putString("login", login)
            editor.putString("password", password)
            editor.apply()
        }else {
            showMessage("Логин и пароль не сохранены")
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun mailCheck(mail:String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
}