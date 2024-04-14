package com.example.apptemple

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.apptemple.databinding.ActivityNewsFeedBinding
import com.example.apptemple.utils.replace

class News_Feed : AppCompatActivity() {
    private val binding by lazy { ActivityNewsFeedBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replace(home())
        announcesPanel()
        bottomOpen()
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed(){
        AlertDialog.Builder(this)
            .setTitle("Выход")
            .setMessage("Вы уверены, что хотите выйти из приложения?")
            .setPositiveButton("Да") { _, _ ->
                finishAffinity() // Завершение всех активностей в приложении
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss() // Отмена выхода
            }
            .show()
    }
    private fun bottomOpen() {
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem -> replace(home())
                R.id.profileItem -> replace(profile())
                R.id.lessonsItem -> replace(lessons())
            }
            true
        }
    }
    private fun announcesPanel() {

    }
}