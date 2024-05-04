package com.example.apptemple

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.apptemple.databinding.FragmentHomeBinding
import com.example.apptemple.utils.navigate

class home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var currentImageIndex = 0
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

        binding.announceLeftButton.setOnClickListener {
            /*if (currentImageIndex > 0) {
                currentImageIndex--
                // Устанавливаем новый индекс
                binding.imageSlider.setCurrentPosition(currentImageIndex)
            }*/
        }

        binding.announceRightButton.setOnClickListener {
            /*if (currentImageIndex < slideImages.size - 1) {
                currentImageIndex++
                // Устанавливаем новый индекс
                binding.imageSlider.setCurrentPosition(currentImageIndex)
            }*/
        }

        binding.imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                Toast.makeText(context, "Вы нажали на ${position + 1} изображение", Toast.LENGTH_SHORT).show()
            }

            override fun doubleClick(position: Int) {
                // Обработка двойного нажатия (если необходимо)
            }
        })
    }
    private fun goSettings() {
        binding.buttonSettings.setOnClickListener {
            requireContext().navigate(SettingsActivity::class.java)
        }
    }
}