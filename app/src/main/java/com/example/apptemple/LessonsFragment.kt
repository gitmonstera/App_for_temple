package com.example.apptemple

import androidx.core.content.res.ResourcesCompat
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.apptemple.databinding.FragmentLessonsBinding

class LessonsFragment : Fragment() {
private lateinit var binding: FragmentLessonsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLessonsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (i in 0 until 5){
            lessonsUpdater()
        }
    }

    private fun lessonsUpdater() {
        val lessonsLinear = LinearLayout(requireContext())
        lessonsLinear.orientation = LinearLayout.HORIZONTAL
        lessonsLinear.setBackgroundResource(R.drawable.gray_rectangle)
        lessonsLinear.setPadding(25, 10, 25, 10)
        lessonsLinear.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val params = lessonsLinear.layoutParams as LinearLayout.LayoutParams
        params.setMargins(20, 10, 20, 10)
        lessonsLinear.layoutParams = params

        val lessonsTitle = TextView(requireContext())
        lessonsTitle.text = "Кружок рисования"
        lessonsTitle.gravity = Gravity.CENTER_VERTICAL
        lessonsTitle.setTextColor(resources.getColor(R.color.black))
        lessonsTitle.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1F
        )

        val buttonWidthInDp = 120
        val buttonWidthInPx = (buttonWidthInDp * resources.displayMetrics.density).toInt()

        val lessonsButton = Button(requireContext())
        lessonsButton.text = "Подробнее"
        lessonsButton.setTextColor(resources.getColor(R.color.black))
        lessonsButton.setBackgroundResource(R.drawable.button_shape)
        lessonsButton.textAlignment = View.TEXT_ALIGNMENT_CENTER
        lessonsButton.gravity = Gravity.END
        lessonsButton.layoutParams = LinearLayout.LayoutParams(
            buttonWidthInPx, // Ширина кнопки
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val typefaceTitle = ResourcesCompat.getFont(requireContext(), R.font.montserrat_regular)
        lessonsTitle.typeface = typefaceTitle

        val typefaceButton = ResourcesCompat.getFont(requireContext(), R.font.montserrat_regular)
        lessonsButton.typeface = typefaceButton

        lessonsLinear.addView(lessonsTitle)
        lessonsLinear.addView(lessonsButton)
        binding.lessonsScroll.addView(lessonsLinear)
    }
}