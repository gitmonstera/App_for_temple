package com.example.apptemple

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
    private lateinit var gestureDetector: GestureDetectorCompat
    private var counter = 0

    companion object {
        private const val TAG = "HomeFragment"
        private const val COUNTER_KEY = "counter_key"
        private const val MAX_COUNTER = 4
        private const val MIN_COUNTER = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            counter = it.getInt(COUNTER_KEY, 0)
        }

        gestureDetector = GestureDetectorCompat(requireContext(), GestureListener())

        // Установка OnTouchListener для viewFinder
        binding.announceSlider.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        changeAnnounces(null)
    }

    private fun changeAnnounces(isSwipeRight: Boolean?) {
        if (isSwipeRight == null) {
            updateImage()
        } else {
            animateImageItem(binding.announceSlider, isSwipeRight) {
                updateImage()
                animateImageIn(binding.announceSlider, isSwipeRight)
            }
        }
    }

    private fun updateImage() {
        when(counter) {
            0 -> binding.announceSlider.setImageResource(R.mipmap.justannounce)
            1 -> binding.announceSlider.setImageResource(R.mipmap.justannounce2)
            2 -> binding.announceSlider.setImageResource(R.mipmap.justannounce3)
            3 -> binding.announceSlider.setImageResource(R.mipmap.justannounce4)
            4 -> binding.announceSlider.setImageResource(R.mipmap.justannounce5)
        }
    }

    private fun animateImageItem (imageView: ImageView, isSwipeRight: Boolean, onAnimationEnd: () -> Unit) {
        val fromXDelta = if (isSwipeRight) 0f else imageView.width.toFloat()
        val toXDelta = if (isSwipeRight) -imageView.width.toFloat() else 0f

        val slideOut = TranslateAnimation(fromXDelta, toXDelta, 0f, 0f)
        slideOut.duration = 300

        slideOut.setAnimationListener(object  : Animation.AnimationListener {

            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                onAnimationEnd()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })

        imageView.startAnimation(slideOut)
    }

    private fun animateImageIn(imageView: ImageView, isSwipeRight: Boolean) {
        val fromXDelta = if (isSwipeRight) -imageView.width.toFloat() else imageView.width.toFloat()
        val toXDelta = 0f

        val slideIn = TranslateAnimation(fromXDelta, toXDelta, 0f, 0f)
        slideIn.duration = 300

        imageView.startAnimation(slideIn)
    }

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

        private fun onSwipeRight() {
            //Обработчик свайпа вправо
            Log.d(TAG, "Свайп вправо обнаружен")
            if (counter > MIN_COUNTER) {
                counter--
                changeAnnounces(isSwipeRight = true)
            } else {
                Toast.makeText(context, "Достигнут предел слева", Toast.LENGTH_SHORT).show()
            }
        }

        private fun onSwipeLeft() {
            //Обработчик свайпа влево
            Log.d(TAG, "Свайп влево обнаружен")
            if (counter < MAX_COUNTER) {
                counter++
                changeAnnounces(isSwipeRight = false)
            } else {
                Toast.makeText(context, "Достигнут предел справа", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_KEY, counter)
    }
}