package com.ruslangrigoriev.exchangerates.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ruslangrigoriev.exchangerates.presentation.fragments.ConverterFragment
import com.ruslangrigoriev.exchangerates.presentation.fragments.CurrencyFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CurrencyFragment()
            else -> ConverterFragment()
        }
    }


}