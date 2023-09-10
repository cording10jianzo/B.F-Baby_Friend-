package com.example.coding10

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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
//            binding.textView.text = result
        }
    }

    private fun search(name: String): Any {
        //dataList에서 이름 일치하는 친구 필터링하기
        val foundFriends = dataList.filter { it.aName == name }
        return if (foundFriends.isNotEmpty()) {
            val position = dataList.indexOf(foundFriends[0])
            val page = Intent(this, DetailActivity::class.java).apply {
                putExtra("DATA", dataList[position])
                putExtra("POS", position)
            }
            resultLauncher.launch(page)
        } else {
            showtoast("일치하는 친구를 찾을 수 없습니다.")
        }
    }

    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
            }
        }
}