package com.example.apptemple

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.apptemple.databinding.FragmentLessonsBinding
import com.example.apptemple.utils.getActivityContext
import com.example.apptemple.utils.navigate

class lessons : Fragment() {
    private lateinit var binding: FragmentLessonsBinding
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
    ): View {
        binding = FragmentLessonsBinding.inflate(inflater, container, false)
        goSettings()
        return binding.root
    }
    private fun goSettings(){
        binding.buttonSettings.setOnClickListener{
            getActivityContext().navigate(SettingsActivity::class.java)
        }
    }
}