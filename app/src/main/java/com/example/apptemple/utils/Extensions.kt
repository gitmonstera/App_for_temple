package com.example.apptemple.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.apptemple.R

//функция расширения для AppCompatActivity, которое позволяет заменить фрагмент на экране
fun AppCompatActivity.replace(
    fragment : Fragment, id : Int = R.id.frameLayout
) = supportFragmentManager.beginTransaction().replace(id, fragment).commit()
//выводит сообщение на экран. Пример использования: displayMessage("Все поля должны быть заполнены!")
fun Context.displayMessage(message : String) = Toast.makeText(
    this, message, Toast.LENGTH_LONG
).show()
//переход на экран. Пример использования: navigate(Enter_Form::class.java)
fun<T> Context.navigate(javaClass : Class<T>) {
    val intent = Intent(this, javaClass)
    startActivity(intent)
}
