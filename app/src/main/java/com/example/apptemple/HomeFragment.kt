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
    }

    //Функция для сохранения текущего состояния
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_KEY, counter)
    }
}