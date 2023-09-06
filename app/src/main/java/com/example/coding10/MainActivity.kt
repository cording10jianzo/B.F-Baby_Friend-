package com.example.coding10

import CustomDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import com.example.coding10.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewPagerAdapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.mainViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> binding.mainFab.show()
                    else -> binding.mainFab.hide()
                }
            }
        })
    }
    fun initView() = with(binding){

        mainViewPager.adapter = viewPagerAdapter

        TabLayoutMediator(mainTabLayout, mainViewPager){ tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
            tab.setIcon(viewPagerAdapter.getIcon(position))
        }.attach()

        mainFab.setOnClickListener {

            CustomDialog(onSave = {item: MainItems ->
                dataList.add(item)
            }).show(supportFragmentManager,"")


        }


    }
}