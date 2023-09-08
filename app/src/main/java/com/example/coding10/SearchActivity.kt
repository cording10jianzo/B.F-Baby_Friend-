package com.example.coding10

import android.os.Bundle
import com.example.coding10.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showtoast("친구 찾기 페이지로 이동합니다.")

        binding.fabBackButton.setOnClickListener {
            finish()
        }

        binding.fabFindButton.setOnClickListener {
            val name = binding.fabName.text.toString().trim()
            val result = search(name)
            binding.textView.text = result
        }
    }

    private fun search(name: String): String {
        //dataList에서 이름 일치하는 친구 필터링하기
        val foundFriends = dataList.filter { it.aName == name }
        return if (foundFriends.isNotEmpty()) {
            val friendName = foundFriends.joinToString { it.aName }
            "$friendName"
        } else {
            "일치하는 친구를 찾을 수 없습니다."
        }
    }
}