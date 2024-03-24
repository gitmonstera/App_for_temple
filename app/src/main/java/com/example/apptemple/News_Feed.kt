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
import com.example.apptemple.databinding.ActivityNewsFeedBinding

class News_Feed : AppCompatActivity() {
    private val binding by lazy { ActivityNewsFeedBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonMenuSwitch.setOnClickListener{
            binding.main.openDrawer(GravityCompat.END)
        }
        goSettings()
        exitAccount()
    }
    private fun goSettings(){
        binding.buttonSettings.setOnClickListener {
            val intent = Intent(this, Settings_Form::class.java)
            binding.main.closeDrawer(GravityCompat.END)
            startActivity(intent)
        }
    }
    private fun exitAccount(){
        binding.buttonExit.setOnClickListener{
            binding.main.closeDrawer(GravityCompat.END)
            finish()
        }
    }
}