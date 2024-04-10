package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.apptemple.databinding.FragmentHomeBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        sliderImages()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goSettings()
    }

    private fun sliderImages() {
        val slideImages = ArrayList<SlideModel>()

        slideImages.add(SlideModel(R.mipmap.justannounce, ScaleTypes.CENTER_CROP))
        slideImages.add(SlideModel(R.mipmap.justannounce2, ScaleTypes.CENTER_CROP))
        slideImages.add(SlideModel(R.mipmap.justannounce3, ScaleTypes.CENTER_CROP))
        slideImages.add(SlideModel(R.mipmap.justannounce4, ScaleTypes.CENTER_CROP))
        slideImages.add(SlideModel(R.mipmap.justannounce5, ScaleTypes.CENTER_CROP))

        binding.imageSlider.setImageList(slideImages)
    }

    private fun goSettings() {
        binding.buttonSettings.setOnClickListener {
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}