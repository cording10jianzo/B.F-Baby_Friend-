package com.example.coding10

import CustomDialog
import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.graphics.Color
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import com.example.coding10.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

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

        binding.fabPlus.setOnClickListener {
            CustomDialog(onSave = { item: MainItems ->
                dataList.add(item)
            }).show(supportFragmentManager, "")
        }

        binding.fabFind.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        // spinner
        val spinnerData = arrayOf("List View", "Grid View")
        val adapter = ArrayAdapter(this, R.layout.spinner_custom_layout, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.mainViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.mainFab.show()
                        binding.fabPlus.show()
                        binding.fabFind.show()
                    }

                    else -> {
                        binding.mainFab.hide()
                        binding.fabPlus.hide()
                        binding.fabFind.hide()
                    }
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

        mainPageBtnEnd.setOnClickListener {

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("종료")
            builder.setIcon(R.mipmap.power)
            builder.setMessage("정말 종료하시겠습니까?")
            val listener = DialogInterface.OnClickListener { _, i ->
                if (i == DialogInterface.BUTTON_POSITIVE) {
                    finish()
                }
            }
            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", null)
            builder.show()

        }

        val itemArray = arrayOf("List View", "Grid View")
        val spinnerAdapter =
            ArrayAdapter(this@MainActivity, R.layout.spinner_dropdown_item, itemArray)
        binding.mainPageSpinner.adapter = spinnerAdapter

        binding.mainPageSpinner.viewTreeObserver.addOnGlobalLayoutListener {
            (binding.mainPageSpinner.selectedView as TextView).setTextColor(Color.YELLOW)
            (binding.mainPageSpinner.selectedView as TextView).setBackgroundResource(R.drawable.spinner_custom)
        }
        binding.mainPageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
//                    Toast.makeText(this@MainActivity, "position 값 : $position", Toast.LENGTH_SHORT).show()
                    (binding.mainViewPager.adapter as MainViewPagerAdapter).switchFragment(position)
                }
                override fun onNothingSelected(parent: AdapterView<*>) {}
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