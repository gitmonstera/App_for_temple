package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

class News_Feed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_feed)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val menuButton: ImageButton = findViewById(R.id.button_MenuSwitch)
        val drawer: DrawerLayout = findViewById(R.id.main)
        val exitButton: Button = findViewById(R.id.button_Exit)
        val switchTheme: Switch = findViewById(R.id.switch_Theme)

        menuButton.setOnClickListener{
            drawer.openDrawer(GravityCompat.END)
        }
        exitAccount(exitButton, drawer)
    }

    fun exitAccount(exitButton: Button, drawer: DrawerLayout){
        exitButton.setOnClickListener{
            val intent = Intent(this, Enter_Form::class.java)
            drawer.closeDrawer(GravityCompat.END)
            startActivity(intent)
        }
    }

    fun themeSwitch(switchTheme: Switch){

    }
}