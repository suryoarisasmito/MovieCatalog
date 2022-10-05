package com.example.moviecatalog.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moviecatalog.*
import com.example.moviecatalog.databinding.ActivityMainBinding
import com.example.moviecatalog.ui.favorite.FavoriteFragment
import com.example.moviecatalog.ui.home.HomeFragment
import com.example.moviecatalog.ui.genres.GenresFragment
import com.example.moviecatalog.ui.popular.PopularFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mOnNavigationItemSelected =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    setCurrentFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.now_playing -> {
                    setCurrentFragment(GenresFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.popular -> {
                    setCurrentFragment(PopularFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    setCurrentFragment(FavoriteFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(HomeFragment())

        initView()

        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelected)

    }


    private fun initView() {
        supportActionBar?.hide()
    }


    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }

}