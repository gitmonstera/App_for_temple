package com.example.apptemple

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import com.example.apptemple.databinding.FragmentHomeBinding
import com.google.android.material.textfield.TextInputLayout.LengthCounter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var gestureDetector: GestureDetectorCompat     //Переменная для считывания жестов
    private var counter = 0

    //Переменные для считывания работы жестов
    companion object {
        private const val TAG = "HomeFragment"
        private const val COUNTER_KEY = "counter_key"
        private const val MAX_COUNTER = 4
        private const val MIN_COUNTER = 0
    }

    //Функция с конфигурацией запуска (тут менять нечего)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Сохранение изначального положения жеста
        savedInstanceState?.let {
            counter = it.getInt(COUNTER_KEY, 0)
        }

        gestureDetector = GestureDetectorCompat(requireContext(), GestureListener())

        // Установка OnTouchListener для viewFinder
        binding.announceSlider.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        changeAnnounces()
    }

    //Статичные фотографии заменить на фотки с сервака
    private fun changeAnnounces() {
        when(counter) {
            0 -> {
                binding.announceSlider.setImageResource(R.mipmap.justannounce)
            }
            1 -> {
                binding.announceSlider.setImageResource(R.mipmap.justannounce2)
            }
            2 -> {
                binding.announceSlider.setImageResource(R.mipmap.justannounce3)
            }
            3 -> {
                binding.announceSlider.setImageResource(R.mipmap.justannounce4)
            }
            4 -> {
                binding.announceSlider.setImageResource(R.mipmap.justannounce5)
            }
        }
    }

    //Функция для считывания жестов и их длины 卐
    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1 == null || e2 == null) return false
            val diffX = e2.x - e1.x
            val diffY = e2.y - e1.y
            return if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight()
                    } else {
                        onSwipeLeft()
                    }
                    true
                } else {
                    false
                }
            } else {
                false
            }
        }

        //Функция для считывания свайпа вправо
        private fun onSwipeRight() {
            //Обработчик свайпа вправо
            Log.d(TAG, "Свайп вправо обнаружен")
            if (counter > MIN_COUNTER) {
                counter--
                changeAnnounces()
            }
        }
        //Функция для считывания свайпа влево
        private fun onSwipeLeft() {
            //Обработчик свайпа влево
            Log.d(TAG, "Свайп влево обнаружен")
            if (counter < MAX_COUNTER) {
                counter++
                changeAnnounces()
            }
        }
    }

    //Функция для сохранения текущего состояния
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_KEY, counter)
    }
}