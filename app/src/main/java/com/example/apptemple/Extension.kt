package com.example.apptemple

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replace(fragment: Fragment,id: Int = R.id.frameLayout) = supportFragmentManager.beginTransaction().replace(id, fragment).commit()