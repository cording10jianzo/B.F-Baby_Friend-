package com.example.coding10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<MainTabs>()

    init{
        fragments.add(
            MainTabs(MainFragment(), "Main Page")
        )
        fragments.add(
            MainTabs(DetailFragment(), "Detail Page")
        )
    }

    fun getTitle(position: Int): String{
        return fragments[position].titleRes
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}