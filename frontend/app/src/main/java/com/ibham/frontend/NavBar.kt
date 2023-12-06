// NavBar.kt
package com.ibham.frontend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ibham.frontend.databinding.NavBarBinding

class NavBar : AppCompatActivity() {

    private lateinit var binding: NavBarBinding

    private val fragmentHome: Fragment = Homepage()
    private val fragmentAbout: Fragment = DictionaryMenu()
    private var activeFragment: Fragment = fragmentHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    switchFragment(fragmentHome)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_about -> {
                    switchFragment(fragmentAbout)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

        switchFragment(activeFragment)
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }
}
