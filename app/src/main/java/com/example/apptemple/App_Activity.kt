package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.apptemple.databinding.ActivityAppBinding

class App_Activity : AppCompatActivity() {
    private val binding by lazy { ActivityAppBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        loadFragment(HomeFragment())
        fragmentChanger()
        toSettingsActivity()
    }

    private fun fragmentChanger() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem -> {
                    loadFragment(HomeFragment())
                    binding.frameName.setText("Главная")
                    true
                }

                R.id.lessonsItem -> {
                    loadFragment(LessonsFragment())
                    binding.frameName.setText("Секции")
                    true
                }

                R.id.profileItem -> {
                    loadFragment(ProfileFragment())
                    binding.frameName.setText("Профиль")
                    true
                }
                else -> false
            }
        }
    }

    private  fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.appFragmentContainer, fragment)
        transaction.commit()
    }

    private fun toSettingsActivity() {
        val intent = Intent(this, Settings_Activity::class.java)
        binding.settingsButton.setOnClickListener {
            startActivity(intent)
        }
    }

}