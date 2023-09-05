package com.example.coding10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<MainTabs>()

    init{
        fragments.add(
            MainTabs(MainFragment(), "Contact", R.drawable.children)
        )
        fragments.add(
            MainTabs(MyPageFragment(), "My Page", R.drawable.teddy_bear)
        )
    }

    fun getTitle(position: Int): String{
        return fragments[position].titleRes
    }

    fun getIcon(position: Int): Int{
        return fragments[position].iconRes
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}