package com.example.apptemple

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.apptemple.databinding.FragmentHomeBinding
import com.example.apptemple.utils.navigate

class home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sliderImages()
        goSettings()
    }
    private fun sliderImages() {
        val slideImages = listOf(
            SlideModel(R.mipmap.justannounce, ScaleTypes.CENTER_CROP),
            SlideModel(R.mipmap.justannounce2, ScaleTypes.CENTER_CROP),
            SlideModel(R.mipmap.justannounce3, ScaleTypes.CENTER_CROP),
            SlideModel(R.mipmap.justannounce4, ScaleTypes.CENTER_CROP),
            SlideModel(R.mipmap.justannounce5, ScaleTypes.CENTER_CROP)
        )
        binding.imageSlider.setImageList(slideImages)
    }
    private fun goSettings() {
        binding.buttonSettings.setOnClickListener {
            requireContext().navigate(SettingsActivity::class.java)
        }
    }
}