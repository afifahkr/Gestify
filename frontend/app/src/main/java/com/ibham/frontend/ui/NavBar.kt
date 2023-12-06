package com.ibham.frontend.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ibham.frontend.R
import com.ibham.frontend.databinding.NavbarBinding
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout

class NavBar : AppCompatActivity() {

    private val fragmentHome: Homepage = Homepage()
    private val fragmentDictionary: DictionaryMenu = DictionaryMenu()
    private val fm: FragmentManager = supportFragmentManager
    private var active: androidx.fragment.app.Fragment = fragmentHome // Ubah tipe data ke Fragment

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem

    private lateinit var binding: NavbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NavbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonNavigation()
    }

    private fun buttonNavigation() {

        fm.beginTransaction().add(R.id.container, fragmentHome, "home").commitAllowingStateLoss()
        fm.beginTransaction().add(R.id.container, fragmentDictionary, "dictionary").hide(fragmentDictionary).commitAllowingStateLoss()


        bottomNavigationView = binding.navView
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.navigation_home -> {
                    slideDown(fragmentHome)
                    callFragment("home", fragmentHome)
                }
                R.id.navigation_dictionary -> {
                    slideUp(fragmentDictionary)
                    callFragment("dictionary", fragmentDictionary)
                }
            }
            true
        }
    }

    fun showDictionaryFragment() {
        callFragment("dictionary", fragmentDictionary)
    }

    private fun slideUp(fragment: androidx.fragment.app.Fragment) {
        val view = fragment.view
        val menuLayout = view?.findViewById<LinearLayout>(R.id.Menu)
        if (view != null) {
            val slideUp = TranslateAnimation(0f, 0f, view.height.toFloat(), 0f)
            slideUp.duration = 500
            if (menuLayout != null) {
                menuLayout.startAnimation(slideUp)
            }
        }
    }

    private fun slideDown(fragment: androidx.fragment.app.Fragment) {
        val view = fragment.view
        val menuLayout = view?.findViewById<LinearLayout>(R.id.homeMenu)
        if (view != null) {
            val slideDown = TranslateAnimation(0f, 0f, 0f, view.height.toFloat())
            slideDown.duration = 500
            if (menuLayout != null) {
                menuLayout.startAnimation(slideDown)
            }
        }
    }


    private fun callFragment(tag: String, fragment: androidx.fragment.app.Fragment) {
        menuItem = menu.findItem(R.id.navigation_home)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commitAllowingStateLoss()
        active = fragment
    }

}
