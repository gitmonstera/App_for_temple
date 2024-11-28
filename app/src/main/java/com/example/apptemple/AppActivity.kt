package com.example.apptemple

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.apptemple.databinding.ActivityAppBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppBinding
    private var pressedTime: Long = 0
    private lateinit var toast: Toast

    // Глобальная переменная для отслеживания активного фрагмента
    private var currentFragmentId: Int = R.id.homeItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Устанавливаем начальный фрагмент, если сохраненного состояния нет
        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), FRAGMENT_DIRECTION_FADE)
            currentFragmentId = R.id.homeItem
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.itemBackground = null

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }

        fragmentChanger()
        toSettingsActivity()
    }

    companion object {
        const val FRAGMENT_DIRECTION_RIGHT = 1
        const val FRAGMENT_DIRECTION_LEFT = 2
        const val FRAGMENT_DIRECTION_FADE = 3
    }

    // Переключатель фрагментов для Боттом бара
    private fun fragmentChanger() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val newFragmentId = item.itemId
            val direction = when {
                currentFragmentId == R.id.homeItem && newFragmentId == R.id.lessonsItem -> FRAGMENT_DIRECTION_RIGHT
                currentFragmentId == R.id.lessonsItem && newFragmentId == R.id.homeItem -> FRAGMENT_DIRECTION_LEFT
                currentFragmentId == R.id.scheduleItem && newFragmentId == R.id.lessonsItem -> FRAGMENT_DIRECTION_LEFT
                currentFragmentId == R.id.lessonsItem && newFragmentId == R.id.scheduleItem -> FRAGMENT_DIRECTION_RIGHT
                else -> FRAGMENT_DIRECTION_FADE
            }

            // Применяем анимацию в зависимости от направления
            loadFragment(getFragmentById(newFragmentId), direction)

            // Смена заголовка при переходе
            val newTitle = when (newFragmentId) {
                R.id.homeItem -> "Главная"
                R.id.lessonsItem -> "Секции"
                R.id.scheduleItem -> "Расписание"
                else -> ""
            }

            // Анимация исчезновения текста
            binding.frameName.animate().alpha(0f).setDuration(200).withEndAction {
                binding.frameName.text = newTitle

                // Анимация появления текста
                binding.frameName.animate().alpha(1f).setDuration(200).start()
            }.start()

            // Обновляем текущий фрагмент
            currentFragmentId = newFragmentId
            true
        }
    }

    // Функция для смены фрагментов
    private fun loadFragment(fragment: Fragment, direction: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        when (direction) {
            FRAGMENT_DIRECTION_RIGHT -> {
                transaction.setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
            }

            FRAGMENT_DIRECTION_LEFT -> {
                transaction.setCustomAnimations(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
            }

            FRAGMENT_DIRECTION_FADE -> {
                transaction.setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
        }

        transaction.replace(R.id.appFragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Функция для получения фрагмента по ID
    private fun getFragmentById(fragmentId: Int): Fragment {
        return when (fragmentId) {
            R.id.homeItem -> HomeFragment()
            R.id.lessonsItem -> LessonsFragment()
            R.id.scheduleItem -> ScheduleFragment()
            else -> HomeFragment()
        }
    }

    // При нажатии на кнопку "Настройки" инициализируется исполнитель и переключает на соответствующий активити
    private fun toSettingsActivity() {
        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    // Функция для игнорирования вызова "super"
    @SuppressLint("MissingSuperCall")

    // Функция для подтверждения выхода двойным нажатием кнопки "Назад"
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - pressedTime < 2000) {
            toast.cancel()
            super.finishAffinity()
            return
        } else {
            toast = Toast.makeText(this, "Нажмите ещё раз для выхода", Toast.LENGTH_SHORT)
            toast.show()
        }
        pressedTime = currentTime
    }
}