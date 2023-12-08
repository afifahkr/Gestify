package com.ibham.frontend.ui.navbarfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibham.frontend.databinding.ActivityHomepageBinding
import com.ibham.frontend.databinding.FragmentAboutUsBinding
import com.ibham.frontend.ui.NavBar
import com.ibham.frontend.ui.RealtimeActivity

class AboutUs : Fragment() {
    private lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding.root
    }
}