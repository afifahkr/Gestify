// Homepage.kt
package com.ibham.frontend.ui.navbarfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibham.frontend.databinding.ActivityHomepageBinding
import com.ibham.frontend.ui.NavBar
import com.ibham.frontend.ui.RealtimeActivity
import com.ibham.frontend.ui.SibiWeb
import com.ibham.frontend.ui.navbarfragment.DictionaryMenu

class Homepage : Fragment() {
    private lateinit var binding: ActivityHomepageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menangani klik pada CardView "Real-Time"
        binding.textView4.setOnClickListener {
            val intent = Intent(activity, RealtimeActivity::class.java)
            startActivity(intent)
        }

        // Menangani klik pada CardView "Kamus"
        binding.textView8.setOnClickListener {
            if (activity is NavBar) {
                (activity as NavBar).showDictionaryFragment()
            }
        }

        binding.sibiWeb.setOnClickListener {
            val intent = Intent(activity, SibiWeb::class.java)
            startActivity(intent)
        }
    }
}
