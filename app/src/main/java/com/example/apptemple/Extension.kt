package com.example.apptemple

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

//функция расширения для AppCompatActivity, которое позволяет заменить фрагмент на экране
fun AppCompatActivity.replace(
    fragment : Fragment, id : Int = R.id.frameLayout
) = supportFragmentManager.beginTransaction().replace(id, fragment).commit()
//выводит сообщение на экран
fun Context.displayMessage(message : String) = Toast.makeText(
    this, message, Toast.LENGTH_LONG
).show()
//переход на экран
fun<T> AppCompatActivity.navigate(javaClass : Class<T>) {
    val intent = Intent(this, javaClass)
    startActivity(intent)
}