// Homepage.kt
package com.ibham.frontend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibham.frontend.databinding.ActivityHomepageBinding // Pastikan path-nya sesuai dengan struktur paket Anda

class Homepage : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangani klik pada CardView "Real-Time"
        binding.textView4.setOnClickListener {
            val intent = Intent(this, RealtimeActivity::class.java)
            startActivity(intent)
        }

        // Menangani klik pada CardView "Kamus"
        binding.textView8.setOnClickListener {
            // Gantilah dengan aksi yang sesuai untuk CardView "Kamus"
            val intent = Intent(this, DictionaryMenu::class.java)
            startActivity(intent)
        }
    }
}
