package com.ruslangrigoriev.exchangerates.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.ruslangrigoriev.exchangerates.R
import com.ruslangrigoriev.exchangerates.databinding.ActivityMainBinding
import com.ruslangrigoriev.exchangerates.presentation.adapters.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun init() {
        binding.apply {
            viewPager.adapter = PagerAdapter(this@MainActivity)
            tabLayout.tabIconTint = null
            TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
                val titles = resources.getStringArray(R.array.tab_titles)
                tab.text = titles[pos]
            }.attach()
        }
    }

    fun setCurrentTab(position: Int) {
        binding.viewPager.currentItem = position
    }

}