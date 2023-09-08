package com.example.coding10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<MainTabs>()
    private var currentSpinnerPosition = 0  //선택된 스피너 저장변수

    init{
        fragments.add(
            MainTabs(MainFragment(), "Contact", R.drawable.children)
//            MainTabs(MainGridFragment(), "Contact", R.drawable.children)
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

    fun switchFragment(position: Int) {
        currentSpinnerPosition = position
        when (position) {
            0 -> fragments[0] = MainTabs(MainFragment(), "Contact", R.drawable.children)
            1 -> fragments[0] = MainTabs(MainGridFragment(), "Contact", R.drawable.children)
        }
        notifyDataSetChanged()
    }
    override fun getItemId(position: Int): Long {
        if (position == 0) return currentSpinnerPosition.toLong()
        if (position == 1) return 999L // 마이페이지는 큰값으로 고정 (교체되지 않도록)
        return position.toLong()
    }


}