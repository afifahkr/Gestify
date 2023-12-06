package com.ibham.frontend

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibham.frontend.databinding.DictionaryMenuBinding

class DictionaryMenu : AppCompatActivity() {
    private lateinit var binding: DictionaryMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DictionaryMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangani klik pada CardView "Kamus"
        binding.abjad.setOnClickListener {
            // Gantilah dengan aksi yang sesuai untuk CardView "Abjad"
            // Contoh:
            // val abjadIntent = Intent(this, AbjadActivity::class.java)
            // startActivity(abjadIntent)
        }

        binding.angka.setOnClickListener {
            // Gantilah dengan aksi yang sesuai untuk CardView "Angka"
        }

        binding.imbuhan.setOnClickListener {
            // Gantilah dengan aksi yang sesuai untuk CardView "Imbuhan"
        }

        binding.kata.setOnClickListener {
            // Gantilah dengan aksi yang sesuai untuk CardView "Kata"
        }
    }
}
