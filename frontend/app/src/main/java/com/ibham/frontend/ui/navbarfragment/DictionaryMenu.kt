// DictionaryMenu.kt
package com.ibham.frontend.ui.navbarfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibham.frontend.databinding.DictionaryMenuBinding
import com.ibham.frontend.ui.kamus.kamusactivity.Abjadlist
import com.ibham.frontend.ui.kamus.kamusactivity.Angkatlist
import com.ibham.frontend.ui.kamus.kamusactivity.Imbuhanlist
import com.ibham.frontend.ui.kamus.kamusactivity.Katalist

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
            val intent = Intent(activity, Abjadlist::class.java)
            startActivity(intent)
        }

        binding.angka.setOnClickListener {
            val intent = Intent(activity, Angkatlist::class.java)
            startActivity(intent)
        }

        binding.imbuhan.setOnClickListener {
            val intent = Intent(activity, Imbuhanlist::class.java)
            startActivity(intent)
        }

        binding.kata.setOnClickListener {
            val intent = Intent(activity, Katalist::class.java)
            startActivity(intent)
        }
    }
}
