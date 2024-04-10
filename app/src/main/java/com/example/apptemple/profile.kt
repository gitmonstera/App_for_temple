package com.example.apptemple

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptemple.databinding.FragmentHomeBinding
import com.example.apptemple.databinding.FragmentProfileBinding

class profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        goSettings()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = profile()
    }

    private fun goSettings(){
        binding.buttonSettings.setOnClickListener{
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}