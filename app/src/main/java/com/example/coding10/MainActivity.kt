package com.example.coding10

import CustomDialog
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import com.example.coding10.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.NonCancellable.start

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var isFabOpen = false

    private val viewPagerAdapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.mainFab.setOnClickListener {
            fabButton()
        }

        // spinner
        val spinnerData = arrayOf("List View", "Grid View")
        val adapter = ArrayAdapter(this, R.layout.spinner_custom_layout, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.mainViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.mainFab.show()
                    else -> binding.mainFab.hide()
                }
            }
        })
    }

    fun initView() = with(binding) {

        mainViewPager.adapter = viewPagerAdapter

        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
            tab.setIcon(viewPagerAdapter.getIcon(position))
        }.attach()

        fabFind.setOnClickListener {

            CustomDialog(onSave = { item: MainItems ->
                dataList.add(item)
            }).show(supportFragmentManager, "")

        }

        fabPlus.setOnClickListener{


        }

    }

    //floating Action Button 메뉴 구현
    private fun fabButton() {

        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션 세팅
        if (isFabOpen) with(binding) {
            ObjectAnimator.ofFloat(fabFind, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(fabPlus, "translationY", 0f).apply { start() }
            mainFab.setImageResource(R.drawable.add)

        // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션 세팅
        } else with(binding) {
            ObjectAnimator.ofFloat(fabFind, "translationY", -150f).apply { start() }
            ObjectAnimator.ofFloat(fabPlus, "translationY", -300f).apply { start() }
            mainFab.setImageResource(R.drawable.add2)
        }

        isFabOpen = !isFabOpen

    }
}