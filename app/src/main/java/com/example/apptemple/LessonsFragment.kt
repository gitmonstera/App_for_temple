package com.example.apptemple

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptemple.databinding.FragmentHomeBinding
import com.example.apptemple.databinding.FragmentLessonsBinding

class LessonsFragment : Fragment() {
private lateinit var binding: FragmentLessonsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLessonsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}