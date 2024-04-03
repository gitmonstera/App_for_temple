package com.example.apptemple

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.apptemple.databinding.ActivityNewsFeedBinding

class News_Feed : AppCompatActivity() {
    private val binding by lazy { ActivityNewsFeedBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            insets
        }
        replaceFragment(home())
        bottomOpen()
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выход")
            .setMessage("Вы уверены, что хотите выйти из приложения?")
            .setPositiveButton("Да") { dialog, which ->
                finishAffinity() // Завершение всех активностей в приложении
            }
            .setNegativeButton("Нет") { dialog, which ->
                dialog.dismiss() // Отмена выхода
            }
            .show()
    }


    private fun bottomOpen(){
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId){

                R.id.homeItem -> {
                    replaceFragment(home())
                }

                R.id.profileItem -> {
                    replaceFragment(profile())
                }

                R.id.lessonsItem -> {
                    replaceFragment(lessons())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}