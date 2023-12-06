// DictionaryMenu.kt
package com.ibham.frontend.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibham.frontend.databinding.DictionaryMenuBinding

class DictionaryMenu : Fragment() {
    private lateinit var binding: DictionaryMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DictionaryMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.abjad.setOnClickListener {

        }

        binding.angka.setOnClickListener {

        }

        binding.imbuhan.setOnClickListener {

        }

        binding.kata.setOnClickListener {

        }
    }
}
